package com.cagiris.coho.service.flight.entity;

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

    private static final long serialVersionUID = 1762016750L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookingDetailsEntity bookingDetailsEntity = new QBookingDetailsEntity("bookingDetailsEntity");

    public final com.cagiris.coho.service.entity.QBaseEntity _super = new com.cagiris.coho.service.entity.QBaseEntity(this);

    public final NumberPath<java.math.BigDecimal> baseFare = createNumber("baseFare", java.math.BigDecimal.class);

    public final EnumPath<com.cagiris.coho.service.flight.api.BookingGDSType> bookingGDSType = createEnum("bookingGDSType", com.cagiris.coho.service.flight.api.BookingGDSType.class);

    public final StringPath bookingId = createString("bookingId");

    public final QCustomerEntity customer;

    //inherited
    public final DateTimePath<java.util.Date> dateAdded = _super.dateAdded;

    //inherited
    public final DateTimePath<java.util.Date> dateModified = _super.dateModified;

    public final NumberPath<java.math.BigDecimal> miscellaneousCharges = createNumber("miscellaneousCharges", java.math.BigDecimal.class);

    public final ListPath<PassengerInfoEntity, QPassengerInfoEntity> passengers = this.<PassengerInfoEntity, QPassengerInfoEntity>createList("passengers", PassengerInfoEntity.class, QPassengerInfoEntity.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigDecimal> taxesAndServiceFee = createNumber("taxesAndServiceFee", java.math.BigDecimal.class);

    public final StringPath userId = createString("userId");

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
    }

}

