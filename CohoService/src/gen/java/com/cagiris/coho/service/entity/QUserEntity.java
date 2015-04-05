package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = 1776112068L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final EnumPath<com.cagiris.coho.service.api.AuthenicationPolicy> authPolicy = createEnum("authPolicy", com.cagiris.coho.service.api.AuthenicationPolicy.class);

    public final StringPath authToken = createString("authToken");

    //inherited
    public final DateTimePath<java.util.Date> dateAdded = _super.dateAdded;

    //inherited
    public final DateTimePath<java.util.Date> dateModified = _super.dateModified;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QOrganizationEntity organizationEntity;

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public final EnumPath<com.cagiris.coho.service.api.UserRole> userRole = createEnum("userRole", com.cagiris.coho.service.api.UserRole.class);

    public QUserEntity(String variable) {
        this(UserEntity.class, forVariable(variable), INITS);
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserEntity(PathMetadata<?> metadata, PathInits inits) {
        this(UserEntity.class, metadata, inits);
    }

    public QUserEntity(Class<? extends UserEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.organizationEntity = inits.isInitialized("organizationEntity") ? new QOrganizationEntity(forProperty("organizationEntity")) : null;
    }

}

