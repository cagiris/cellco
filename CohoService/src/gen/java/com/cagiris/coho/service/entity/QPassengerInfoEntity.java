package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPassengerInfoEntity is a Querydsl query type for PassengerInfoEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPassengerInfoEntity extends EntityPathBase<PassengerInfoEntity> {

    private static final long serialVersionUID = -1519029003L;

    public static final QPassengerInfoEntity passengerInfoEntity = new QPassengerInfoEntity("passengerInfoEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> dateAdded = _super.dateAdded;

    //inherited
    public final DateTimePath<java.util.Date> dateModified = _super.dateModified;

    public final DateTimePath<java.util.Date> dateOfBirth = createDateTime("dateOfBirth", java.util.Date.class);

    public final StringPath firstName = createString("firstName");

    public final StringPath lastName = createString("lastName");

    public final StringPath middleName = createString("middleName");

    public final NumberPath<Long> passengerId = createNumber("passengerId", Long.class);

    public final EnumPath<com.cagiris.coho.service.api.PassengerType> type = createEnum("type", com.cagiris.coho.service.api.PassengerType.class);

    public QPassengerInfoEntity(String variable) {
        super(PassengerInfoEntity.class, forVariable(variable));
    }

    public QPassengerInfoEntity(Path<? extends PassengerInfoEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPassengerInfoEntity(PathMetadata<?> metadata) {
        super(PassengerInfoEntity.class, metadata);
    }

}

