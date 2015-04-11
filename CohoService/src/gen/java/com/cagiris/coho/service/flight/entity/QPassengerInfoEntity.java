package com.cagiris.coho.service.flight.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPassengerInfoEntity is a Querydsl query type for PassengerInfoEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPassengerInfoEntity extends EntityPathBase<PassengerInfoEntity> {

    private static final long serialVersionUID = -875752151L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPassengerInfoEntity passengerInfoEntity = new QPassengerInfoEntity("passengerInfoEntity");

    public final com.cagiris.coho.service.entity.QBaseEntity _super = new com.cagiris.coho.service.entity.QBaseEntity(this);

    public final QBookingDetailsEntity bookingDetailsEntity;

    //inherited
    public final DateTimePath<java.util.Date> dateAdded = _super.dateAdded;

    //inherited
    public final DateTimePath<java.util.Date> dateModified = _super.dateModified;

    public final DateTimePath<java.util.Date> dateOfBirth = createDateTime("dateOfBirth", java.util.Date.class);

    public final StringPath firstName = createString("firstName");

    public final StringPath lastName = createString("lastName");

    public final StringPath middleName = createString("middleName");

    public final NumberPath<java.math.BigInteger> passengerId = createNumber("passengerId", java.math.BigInteger.class);

    public final EnumPath<com.cagiris.coho.service.flight.api.PassengerType> type = createEnum("type", com.cagiris.coho.service.flight.api.PassengerType.class);

    public QPassengerInfoEntity(String variable) {
        this(PassengerInfoEntity.class, forVariable(variable), INITS);
    }

    public QPassengerInfoEntity(Path<? extends PassengerInfoEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPassengerInfoEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPassengerInfoEntity(PathMetadata<?> metadata, PathInits inits) {
        this(PassengerInfoEntity.class, metadata, inits);
    }

    public QPassengerInfoEntity(Class<? extends PassengerInfoEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bookingDetailsEntity = inits.isInitialized("bookingDetailsEntity") ? new QBookingDetailsEntity(forProperty("bookingDetailsEntity"), inits.get("bookingDetailsEntity")) : null;
    }

}

