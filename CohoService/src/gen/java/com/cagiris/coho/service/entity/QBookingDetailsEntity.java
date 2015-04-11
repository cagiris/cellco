package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBookingDetailsEntity is a Querydsl query type for BookingDetailsEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBookingDetailsEntity extends EntityPathBase<BookingDetailsEntity> {

    private static final long serialVersionUID = -999696478L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookingDetailsEntity bookingDetailsEntity = new QBookingDetailsEntity("bookingDetailsEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Double> baseFare = createNumber("baseFare", Double.class);

    public final NumberPath<Long> bookingId = createNumber("bookingId", Long.class);

    public final SetPath<BookingPassengerInfoEntity, QBookingPassengerInfoEntity> bookingVsPassengers = this.<BookingPassengerInfoEntity, QBookingPassengerInfoEntity>createSet("bookingVsPassengers", BookingPassengerInfoEntity.class, QBookingPassengerInfoEntity.class, PathInits.DIRECT2);

    public final QCustomerEntity customer;

    //inherited
    public final DateTimePath<java.util.Date> dateAdded = _super.dateAdded;

    //inherited
    public final DateTimePath<java.util.Date> dateModified = _super.dateModified;

    public final NumberPath<Double> miscellaneousChanrges = createNumber("miscellaneousChanrges", Double.class);

    public final NumberPath<Double> taxesAndServiceFee = createNumber("taxesAndServiceFee", Double.class);

    public final QUserEntity user;

    public QBookingDetailsEntity(String variable) {
        this(BookingDetailsEntity.class, forVariable(variable), INITS);
    }

    public QBookingDetailsEntity(Path<? extends BookingDetailsEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBookingDetailsEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBookingDetailsEntity(PathMetadata<?> metadata, PathInits inits) {
        this(BookingDetailsEntity.class, metadata, inits);
    }

    public QBookingDetailsEntity(Class<? extends BookingDetailsEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomerEntity(forProperty("customer")) : null;
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

