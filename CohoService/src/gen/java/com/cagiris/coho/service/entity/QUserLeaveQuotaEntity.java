package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUserLeaveQuotaEntity is a Querydsl query type for UserLeaveQuotaEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserLeaveQuotaEntity extends EntityPathBase<UserLeaveQuotaEntity> {

    private static final long serialVersionUID = 35417573L;

    public static final QUserLeaveQuotaEntity userLeaveQuotaEntity = new QUserLeaveQuotaEntity("userLeaveQuotaEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> dateAdded = _super.dateAdded;

    //inherited
    public final DateTimePath<java.util.Date> dateModified = _super.dateModified;

    public final MapPath<com.cagiris.coho.service.api.LeaveType, Integer, NumberPath<Integer>> leaveTypeVsLeaveQuota = this.<com.cagiris.coho.service.api.LeaveType, Integer, NumberPath<Integer>>createMap("leaveTypeVsLeaveQuota", com.cagiris.coho.service.api.LeaveType.class, Integer.class, NumberPath.class);

    public final StringPath userId = createString("userId");

    public QUserLeaveQuotaEntity(String variable) {
        super(UserLeaveQuotaEntity.class, forVariable(variable));
    }

    public QUserLeaveQuotaEntity(Path<? extends UserLeaveQuotaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserLeaveQuotaEntity(PathMetadata<?> metadata) {
        super(UserLeaveQuotaEntity.class, metadata);
    }

}

