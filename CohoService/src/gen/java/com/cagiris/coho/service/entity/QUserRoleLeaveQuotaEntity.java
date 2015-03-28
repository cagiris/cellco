package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUserRoleLeaveQuotaEntity is a Querydsl query type for UserRoleLeaveQuotaEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserRoleLeaveQuotaEntity extends EntityPathBase<UserRoleLeaveQuotaEntity> {

    private static final long serialVersionUID = -1260696069L;

    public static final QUserRoleLeaveQuotaEntity userRoleLeaveQuotaEntity = new QUserRoleLeaveQuotaEntity("userRoleLeaveQuotaEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> dateAdded = _super.dateAdded;

    //inherited
    public final DateTimePath<java.util.Date> dateModified = _super.dateModified;

    public final MapPath<com.cagiris.coho.service.api.LeaveType, Integer, NumberPath<Integer>> leaveTypeVsLeaveCount = this.<com.cagiris.coho.service.api.LeaveType, Integer, NumberPath<Integer>>createMap("leaveTypeVsLeaveCount", com.cagiris.coho.service.api.LeaveType.class, Integer.class, NumberPath.class);

    public final EnumPath<com.cagiris.coho.service.api.UserRole> userRole = createEnum("userRole", com.cagiris.coho.service.api.UserRole.class);

    public QUserRoleLeaveQuotaEntity(String variable) {
        super(UserRoleLeaveQuotaEntity.class, forVariable(variable));
    }

    public QUserRoleLeaveQuotaEntity(Path<? extends UserRoleLeaveQuotaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserRoleLeaveQuotaEntity(PathMetadata<?> metadata) {
        super(UserRoleLeaveQuotaEntity.class, metadata);
    }

}

