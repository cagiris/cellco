package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QHolidayEntity is a Querydsl query type for HolidayEntity
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QHolidayEntity extends EntityPathBase<HolidayEntity> {

    private static final long serialVersionUID = 1258408037L;

    public static final QHolidayEntity holidayEntity = new QHolidayEntity("holidayEntity");

    public final StringPath description = createString("description");

    public final NumberPath<Long> organizationId = createNumber("organizationId", Long.class);

    public final EnumPath<com.cagiris.coho.service.api.UserRole> userRole = createEnum("userRole", com.cagiris.coho.service.api.UserRole.class);

    public QHolidayEntity(String variable) {
        super(HolidayEntity.class, forVariable(variable));
    }

    public QHolidayEntity(Path<? extends HolidayEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHolidayEntity(PathMetadata<?> metadata) {
        super(HolidayEntity.class, metadata);
    }

}

