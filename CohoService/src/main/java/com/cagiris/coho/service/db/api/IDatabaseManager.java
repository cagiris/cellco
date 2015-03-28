/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.db.api;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;

import com.cagiris.coho.service.db.impl.CohoDeleteClause;
import com.cagiris.coho.service.db.impl.CohoUpdateClause;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.types.path.EntityPathBase;

/**
 *
 * @author: ssnk
 */

public interface IDatabaseManager {

	Serializable save(Object entity) throws DatabaseManagerException;

	Serializable save(String entityName, Object entity) throws DatabaseManagerException;

	void update(Object entity) throws DatabaseManagerException;

	void update(String entityName, Object entity) throws DatabaseManagerException;

	void saveOrUpdate(Object entity) throws DatabaseManagerException;

	void saveOrUpdate(String entityName, Object entity) throws DatabaseManagerException;

	<T> T get(Class<T> entityClass, Serializable id) throws DatabaseManagerException;

	Object get(String entityName, Serializable id) throws DatabaseManagerException;

	SessionFactory getSessionFactory() throws DatabaseManagerException;

	void delete(Object entity) throws DatabaseManagerException;

	void delete(String entityName, Object entity) throws DatabaseManagerException;

	void deleteAll(Collection<?> entities) throws DatabaseManagerException;

	<T> List<T> executeQueryAndGetResults(HibernateQuery hibernateQuery, EntityPathBase<T> entityPath)
			throws DatabaseManagerException;

	long executeUpdateClause(CohoUpdateClause updateClause) throws DatabaseManagerException;

	long executeDeleteClause(CohoDeleteClause deleteClause) throws DatabaseManagerException;
}
