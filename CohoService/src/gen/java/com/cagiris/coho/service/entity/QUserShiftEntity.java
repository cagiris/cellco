package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUserShiftEntity is a Querydsl query type for UserShiftEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserShiftEntity extends EntityPathBase<UserShiftEntity> {

    private static final long serialVersionUID = 1090546116L;

    public static final QUserShiftEntity userShiftEntity = new QUserShiftEntity("userShiftEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> dateAdded = _super.dateAdded;

    //inherited
    public final DateTimePath<java.util.Date> dateModified = _super.dateModified;

    public final StringPath shiftEndReason = createString("shiftEndReason");

    public final DateTimePath<java.util.Date> shiftEndTime = createDateTime("shiftEndTime", java.util.Date.class);

    public final StringPath shiftId = createString("shiftId");

    public final StringPath shiftStartReason = createString("shiftStartReason");

    public final DateTimePath<java.util.Date> shiftStartTime = createDateTime("shiftStartTime", java.util.Date.class);

    public final NumberPath<Long> teamId = createNumber("teamId", Long.class);

    public final StringPath userId = createString("userId");

    public QUserShiftEntity(String variable) {
        super(UserShiftEntity.class, forVariable(variable));
    }

    public QUserShiftEntity(Path<? extends UserShiftEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserShiftEntity(PathMetadata<?> metadata) {
        super(UserShiftEntity.class, metadata);
    }

}

