/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.db.impl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;

import com.mysema.query.JoinType;
import com.mysema.query.dml.DeleteClause;
import com.mysema.query.jpa.HQLTemplates;
import com.mysema.query.jpa.JPAQueryMixin;
import com.mysema.query.jpa.JPQLSerializer;
import com.mysema.query.jpa.JPQLTemplates;
import com.mysema.query.jpa.hibernate.DefaultSessionHolder;
import com.mysema.query.jpa.hibernate.HibernateUtil;
import com.mysema.query.jpa.hibernate.NoSessionHolder;
import com.mysema.query.jpa.hibernate.SessionHolder;
import com.mysema.query.support.QueryMixin;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.Path;
import com.mysema.query.types.Predicate;

/**
 * From: {@code HibernateDeleteClause}
 * 
 * @author: ssnk
 */

public class CohoDeleteClause implements DeleteClause<CohoDeleteClause> {

	private QueryMixin queryMixin = new JPAQueryMixin();

	private final SessionHolder session;

	private final JPQLTemplates templates;

	private Map<Path<?>, LockMode> lockModes = new HashMap<Path<?>, LockMode>();

	public CohoDeleteClause(EntityPath<?> entity) {
		this.session = NoSessionHolder.DEFAULT;
		this.templates = HQLTemplates.DEFAULT;
		queryMixin.addJoin(JoinType.DEFAULT, entity);
	}

	public CohoDeleteClause(Session session, CohoDeleteClause cohoUpdateClause) {
		this.session = new DefaultSessionHolder(session);
		this.templates = cohoUpdateClause.getTemplates();
		this.lockModes = cohoUpdateClause.getLockModes();
		this.queryMixin = cohoUpdateClause.getQueryMixin();
	}

	@Override
	public long execute() {
		JPQLSerializer serializer = new JPQLSerializer(templates, null);
		serializer.serializeForDelete(queryMixin.getMetadata());
		Map<Object, String> constants = serializer.getConstantToLabel();

		Query query = session.createQuery(serializer.toString());
		for (Map.Entry<Path<?>, LockMode> entry : lockModes.entrySet()) {
			query.setLockMode(entry.getKey().toString(), entry.getValue());
		}
		HibernateUtil.setConstants(query, constants, queryMixin.getMetadata().getParams());
		return query.executeUpdate();
	}

	@Override
	public CohoDeleteClause where(Predicate... o) {
		for (Predicate p : o) {
			queryMixin.where(p);
		}
		return this;
	}

	/**
	 * Set the lock mode for the given path.
	 */
	@SuppressWarnings("unchecked")
	public CohoDeleteClause setLockMode(Path<?> path, LockMode lockMode) {
		lockModes.put(path, lockMode);
		return this;
	}

	@Override
	public String toString() {
		JPQLSerializer serializer = new JPQLSerializer(templates, null);
		serializer.serializeForDelete(queryMixin.getMetadata());
		return serializer.toString();
	}

	public Map<Path<?>, LockMode> getLockModes() {
		return lockModes;
	}

	public void setLockModes(Map<Path<?>, LockMode> lockModes) {
		this.lockModes = lockModes;
	}

	public QueryMixin getQueryMixin() {
		return queryMixin;
	}

	public JPQLTemplates getTemplates() {
		return templates;
	}

}
