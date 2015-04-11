package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBookingPassengerInfoEntity is a Querydsl query type for BookingPassengerInfoEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBookingPassengerInfoEntity extends EntityPathBase<BookingPassengerInfoEntity> {

    private static final long serialVersionUID = 370157448L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookingPassengerInfoEntity bookingPassengerInfoEntity = new QBookingPassengerInfoEntity("bookingPassengerInfoEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QBookingDetailsEntity booking;

    //inherited
    public final DateTimePath<java.util.Date> dateAdded = _super.dateAdded;

    //inherited
    public final DateTimePath<java.util.Date> dateModified = _super.dateModified;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPassengerInfoEntity passenger;

    public QBookingPassengerInfoEntity(String variable) {
        this(BookingPassengerInfoEntity.class, forVariable(variable), INITS);
    }

    public QBookingPassengerInfoEntity(Path<? extends BookingPassengerInfoEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBookingPassengerInfoEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBookingPassengerInfoEntity(PathMetadata<?> metadata, PathInits inits) {
        this(BookingPassengerInfoEntity.class, metadata, inits);
    }

    public QBookingPassengerInfoEntity(Class<? extends BookingPassengerInfoEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.booking = inits.isInitialized("booking") ? new QBookingDetailsEntity(forProperty("booking"), inits.get("booking")) : null;
        this.passenger = inits.isInitialized("passenger") ? new QPassengerInfoEntity(forProperty("passenger")) : null;
    }

}

