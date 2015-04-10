package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QWeeklyHolidayEntity is a Querydsl query type for WeeklyHolidayEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QWeeklyHolidayEntity extends EntityPathBase<WeeklyHolidayEntity> {

    private static final long serialVersionUID = -1058741788L;

    public static final QWeeklyHolidayEntity weeklyHolidayEntity = new QWeeklyHolidayEntity("weeklyHolidayEntity");

    public final QHolidayEntity _super = new QHolidayEntity(this);

    //inherited
    public final StringPath description = _super.description;

    public final NumberPath<Long> holidayId = createNumber("holidayId", Long.class);

    //inherited
    public final NumberPath<Long> organizationId = _super.organizationId;

    //inherited
    public final EnumPath<com.cagiris.coho.service.api.UserRole> userRole = _super.userRole;

    public final NumberPath<Integer> weekDay = createNumber("weekDay", Integer.class);

    public QWeeklyHolidayEntity(String variable) {
        super(WeeklyHolidayEntity.class, forVariable(variable));
    }

    public QWeeklyHolidayEntity(Path<? extends WeeklyHolidayEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWeeklyHolidayEntity(PathMetadata<?> metadata) {
        super(WeeklyHolidayEntity.class, metadata);
    }

}

