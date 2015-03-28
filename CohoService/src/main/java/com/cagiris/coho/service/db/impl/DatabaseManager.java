/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.db.impl;

import java.io.Serializable;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.cagiris.coho.service.db.api.DatabaseManagerException;
import com.cagiris.coho.service.db.api.IDatabaseManager;

/**
 *
 * @author: ssnk
 */

public class DatabaseManager implements IDatabaseManager {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);
	private static final String DEFAULT_HIBERNATE_CONGFIG_FILE_NAME = "hibernate.cfg.xml";
	private SessionFactory sessionFactory;
	private String[] packagesToScan;

	public DatabaseManager() {
		packagesToScan = new String[] { "com.cagiris.coho.service.entity" };
		String configFileName = DEFAULT_HIBERNATE_CONGFIG_FILE_NAME;

		logger.info("Using hibernate config file:{}", configFileName);

		URL resourceURL = this.getClass().getClassLoader().getResource(configFileName);

		Configuration configuration = new Configuration().configure(resourceURL);
		Set<Class<?>> entityClasses = scanAndGetAllClasses();
		for (Class<?> clazz : entityClasses) {
			configuration.addAnnotatedClass(clazz);
		}
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(configuration.getProperties());
		this.sessionFactory = configuration.buildSessionFactory(builder.build());
	}

	private Set<Class<?>> scanAndGetAllClasses() {
		ClassPathScanningCandidateComponentProvider candidateComponentProvider = new ClassPathScanningCandidateComponentProvider(
				false);
		candidateComponentProvider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
		Set<Class<?>> classes = new HashSet<Class<?>>();
		for (String packageName : packagesToScan) {
			for (BeanDefinition beanDefinition : candidateComponentProvider.findCandidateComponents(packageName)) {
				String beanClassName = beanDefinition.getBeanClassName();
				try {
					Class<?> entityClass = Class.forName(beanClassName);
					classes.add(entityClass);
				} catch (ClassNotFoundException e) {
					logger.error("Failed to load entity class:{}", beanClassName, e);
				}
			}
		}
		return classes;
	}

	@Override
	public Serializable save(Object entity) throws DatabaseManagerException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Serializable save = session.save(entity);
			tx.commit();
			return save;
		} catch (HibernateException e) {
			rollbackTransaction(tx);
			throw new DatabaseManagerException(e);
		} finally {
			closeSession(session);
		}
	}

	private void rollbackTransaction(Transaction tx) {
		if (tx != null) {
			try {
				tx.rollback();
			} catch (HibernateException e1) {
				logger.error("Error during rollback : {}", e1.getMessage(), e1);
			}
		}
	}

	private void closeSession(Session session) {
		if (session != null) {
			try {
				session.close();
			} catch (HibernateException e) {
				logger.error("Error while closing session: {}", e.getMessage(), e);
			}
		}
	}

	@Override
	public Serializable save(String entityName, Object entity) throws DatabaseManagerException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Serializable save = session.save(entityName, entity);
			tx.commit();
			return save;
		} catch (HibernateException e) {
			rollbackTransaction(tx);
			throw new DatabaseManagerException(e);
		} finally {
			closeSession(session);
		}
	}

	@Override
	public void update(Object entity) throws DatabaseManagerException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(entity);
			tx.commit();
		} catch (HibernateException e) {
			rollbackTransaction(tx);
			throw new DatabaseManagerException(e);
		} finally {
			closeSession(session);
		}
	}

	@Override
	public void update(String entityName, Object entity) throws DatabaseManagerException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(entityName, entity);
			tx.commit();
		} catch (HibernateException e) {
			rollbackTransaction(tx);
			throw new DatabaseManagerException(e);
		} finally {
			closeSession(session);
		}
	}

	@Override
	public void saveOrUpdate(Object entity) throws DatabaseManagerException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(entity);
			tx.commit();
		} catch (HibernateException e) {
			rollbackTransaction(tx);
			throw new DatabaseManagerException(e);
		} finally {
			closeSession(session);
		}
	}

	@Override
	public void saveOrUpdate(String entityName, Object entity) throws DatabaseManagerException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(entityName, entity);
			tx.commit();
		} catch (HibernateException e) {
			rollbackTransaction(tx);
			throw new DatabaseManagerException(e);
		} finally {
			closeSession(session);
		}
	}

	@Override
	public <T> T get(Class<T> entityClass, Serializable id) throws DatabaseManagerException {
		Session session = sessionFactory.openSession();
		try {
			return (T) session.get(entityClass, id);
		} catch (HibernateException e) {
			throw new DatabaseManagerException(e);
		} finally {
			closeSession(session);
		}
	}

	@Override
	public Object get(String entityName, Serializable id) throws DatabaseManagerException {
		Session session = sessionFactory.openSession();
		try {
			return session.get(entityName, id);
		} catch (HibernateException e) {
			throw new DatabaseManagerException(e);
		} finally {
			closeSession(session);
		}
	}

	@Override
	public SessionFactory getSessionFactory() throws DatabaseManagerException {
		return this.sessionFactory;
	}

	@Override
	public void delete(Object entity) throws DatabaseManagerException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(entity);
			tx.commit();
		} catch (HibernateException e) {
			rollbackTransaction(tx);
			throw new DatabaseManagerException(e);
		} finally {
			closeSession(session);
		}
	}

	@Override
	public void delete(String entityName, Object entity) throws DatabaseManagerException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(entityName, entity);
			tx.commit();
		} catch (HibernateException e) {
			rollbackTransaction(tx);
			throw new DatabaseManagerException(e);
		} finally {
			closeSession(session);
		}
	}

	@Override
	public void deleteAll(Collection<?> entities) throws DatabaseManagerException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (Object entity : entities) {
				session.delete(entity);
			}
			tx.commit();
		} catch (HibernateException e) {
			rollbackTransaction(tx);
			throw new DatabaseManagerException(e);
		} finally {
			closeSession(session);
		}
	}

}
