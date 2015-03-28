package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUserLeaveRequestEntity is a Querydsl query type for UserLeaveRequestEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserLeaveRequestEntity extends EntityPathBase<UserLeaveRequestEntity> {

    private static final long serialVersionUID = 1731445308L;

    public static final QUserLeaveRequestEntity userLeaveRequestEntity = new QUserLeaveRequestEntity("userLeaveRequestEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath approvingUserId = createString("approvingUserId");

    //inherited
    public final DateTimePath<java.util.Date> dateAdded = _super.dateAdded;

    //inherited
    public final DateTimePath<java.util.Date> dateModified = _super.dateModified;

    public final StringPath leaveApplicationId = createString("leaveApplicationId");

    public final EnumPath<com.cagiris.coho.service.api.LeaveRequestStatus> leaveApplicationStatus = createEnum("leaveApplicationStatus", com.cagiris.coho.service.api.LeaveRequestStatus.class);

    public final MapPath<com.cagiris.coho.service.api.LeaveType, Integer, NumberPath<Integer>> leaveTypeVsLeaveCount = this.<com.cagiris.coho.service.api.LeaveType, Integer, NumberPath<Integer>>createMap("leaveTypeVsLeaveCount", com.cagiris.coho.service.api.LeaveType.class, Integer.class, NumberPath.class);

    public final StringPath userId = createString("userId");

    public QUserLeaveRequestEntity(String variable) {
        super(UserLeaveRequestEntity.class, forVariable(variable));
    }

    public QUserLeaveRequestEntity(Path<? extends UserLeaveRequestEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserLeaveRequestEntity(PathMetadata<?> metadata) {
        super(UserLeaveRequestEntity.class, metadata);
    }

}

