package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUserProfileEntity is a Querydsl query type for UserProfileEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserProfileEntity extends EntityPathBase<UserProfileEntity> {

    private static final long serialVersionUID = 1235753451L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserProfileEntity userProfileEntity = new QUserProfileEntity("userProfileEntity");

    public final StringPath address = createString("address");

    public final StringPath city = createString("city");

    public final DateTimePath<java.util.Date> dateOfBirth = createDateTime("dateOfBirth", java.util.Date.class);

    public final StringPath email = createString("email");

    public final StringPath phone = createString("phone");

    public final StringPath postalCode = createString("postalCode");

    public final QUserEntity userEntity;

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public final DateTimePath<java.util.Date> workEndDate = createDateTime("workEndDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> workStartDate = createDateTime("workStartDate", java.util.Date.class);

    public QUserProfileEntity(String variable) {
        this(UserProfileEntity.class, forVariable(variable), INITS);
    }

    public QUserProfileEntity(Path<? extends UserProfileEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserProfileEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserProfileEntity(PathMetadata<?> metadata, PathInits inits) {
        this(UserProfileEntity.class, metadata, inits);
    }

    public QUserProfileEntity(Class<? extends UserProfileEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity"), inits.get("userEntity")) : null;
    }

}

