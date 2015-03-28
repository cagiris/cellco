package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = 1776112068L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final EnumPath<com.cagiris.coho.service.api.AuthenicationPolicy> authPolicy = createEnum("authPolicy", com.cagiris.coho.service.api.AuthenicationPolicy.class);

    public final StringPath authToken = createString("authToken");

    //inherited
    public final DateTimePath<java.util.Date> dateAdded = _super.dateAdded;

    //inherited
    public final DateTimePath<java.util.Date> dateModified = _super.dateModified;

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public final EnumPath<com.cagiris.coho.service.api.UserRole> userRole = createEnum("userRole", com.cagiris.coho.service.api.UserRole.class);

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata<?> metadata) {
        super(UserEntity.class, metadata);
    }

}

