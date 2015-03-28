/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.db.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;

import com.mysema.query.JoinType;
import com.mysema.query.dml.UpdateClause;
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
import com.mysema.query.types.Expression;
import com.mysema.query.types.ExpressionUtils;
import com.mysema.query.types.NullExpression;
import com.mysema.query.types.Path;
import com.mysema.query.types.Predicate;

/**
 * From {@code HibernateUpdateClause}
 * 
 * @author: ssnk
 */

public class CohoUpdateClause implements UpdateClause<CohoUpdateClause> {

	private QueryMixin queryMixin = new JPAQueryMixin();

	private final SessionHolder session;

	private final JPQLTemplates templates;

	private Map<Path<?>, LockMode> lockModes = new HashMap<Path<?>, LockMode>();

	public CohoUpdateClause(EntityPath<?> entity) {
		this.session = NoSessionHolder.DEFAULT;
		this.templates = HQLTemplates.DEFAULT;
		queryMixin.addJoin(JoinType.DEFAULT, entity);
	}

	public CohoUpdateClause(Session session, CohoUpdateClause cohoUpdateClause) {
		this.session = new DefaultSessionHolder(session);
		this.templates = cohoUpdateClause.getTemplates();
		this.lockModes = cohoUpdateClause.getLockModes();
		this.queryMixin = cohoUpdateClause.getQueryMixin();
	}

	@Override
	public long execute() {
		JPQLSerializer serializer = new JPQLSerializer(getTemplates(), null);
		serializer.serializeForUpdate(queryMixin.getMetadata());
		Map<Object, String> constants = serializer.getConstantToLabel();

		Query query = session.createQuery(serializer.toString());
		for (Map.Entry<Path<?>, LockMode> entry : lockModes.entrySet()) {
			query.setLockMode(entry.getKey().toString(), entry.getValue());
		}
		HibernateUtil.setConstants(query, constants, queryMixin.getMetadata().getParams());
		return query.executeUpdate();
	}

	@Override
	public <T> CohoUpdateClause set(Path<T> path, T value) {
		if (value != null) {
			queryMixin.addProjection(ExpressionUtils.eqConst(path, value));
		} else {
			setNull(path);
		}
		return this;
	}

	@Override
	public <T> CohoUpdateClause set(Path<T> path, Expression<? extends T> expression) {
		if (expression != null) {
			queryMixin.addProjection(ExpressionUtils.eq(path, expression));
		} else {
			setNull(path);
		}
		return this;
	}

	@Override
	public <T> CohoUpdateClause setNull(Path<T> path) {
		queryMixin.addProjection(ExpressionUtils.eq(path, new NullExpression<T>(path.getType())));
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CohoUpdateClause set(List<? extends Path<?>> paths, List<?> values) {
		for (int i = 0; i < paths.size(); i++) {
			if (values.get(i) != null) {
				queryMixin.addProjection(ExpressionUtils.eqConst((Expression) paths.get(i), values.get(i)));
			} else {
				queryMixin.addProjection(ExpressionUtils.eq(((Expression) paths.get(i)), new NullExpression(paths
						.get(i).getType())));
			}

		}
		return this;
	}

	@Override
	public CohoUpdateClause where(Predicate... o) {
		for (Predicate p : o) {
			queryMixin.where(p);
		}
		return this;
	}

	/**
	 * Set the lock mode for the given path.
	 */
	@SuppressWarnings("unchecked")
	public CohoUpdateClause setLockMode(Path<?> path, LockMode lockMode) {
		lockModes.put(path, lockMode);
		return this;
	}

	@Override
	public String toString() {
		JPQLSerializer serializer = new JPQLSerializer(getTemplates(), null);
		serializer.serializeForUpdate(queryMixin.getMetadata());
		return serializer.toString();
	}

	@Override
	public boolean isEmpty() {
		return queryMixin.getMetadata().getProjection().isEmpty();
	}

	public QueryMixin getQueryMixin() {
		return queryMixin;
	}

	public Map<Path<?>, LockMode> getLockModes() {
		return lockModes;
	}

	public JPQLTemplates getTemplates() {
		return templates;
	}
}
