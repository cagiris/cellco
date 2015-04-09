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

    public final StringPath addressLine1 = createString("addressLine1");

    public final StringPath addressLine2 = createString("addressLine2");

    public final StringPath city = createString("city");

    public final StringPath country = createString("country");

    public final DateTimePath<java.util.Date> dateOfBirth = createDateTime("dateOfBirth", java.util.Date.class);

    public final StringPath designation = createString("designation");

    public final StringPath emailId = createString("emailId");

    public final StringPath firstName = createString("firstName");

    public final StringPath gender = createString("gender");

    public final DateTimePath<java.util.Date> joinedOn = createDateTime("joinedOn", java.util.Date.class);

    public final StringPath lastName = createString("lastName");

    public final DateTimePath<java.util.Date> leftOn = createDateTime("leftOn", java.util.Date.class);

    public final StringPath mobileNumber = createString("mobileNumber");

    public final StringPath pincode = createString("pincode");

    public final StringPath state = createString("state");

    public final QUserEntity userEntity;

    public final StringPath userId = createString("userId");

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

