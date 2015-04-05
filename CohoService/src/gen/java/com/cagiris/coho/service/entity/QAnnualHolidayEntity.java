package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAnnualHolidayEntity is a Querydsl query type for AnnualHolidayEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAnnualHolidayEntity extends EntityPathBase<AnnualHolidayEntity> {

    private static final long serialVersionUID = 1314658630L;

    public static final QAnnualHolidayEntity annualHolidayEntity = new QAnnualHolidayEntity("annualHolidayEntity");

    public final QHolidayEntity _super = new QHolidayEntity(this);

    public final NumberPath<Integer> day = createNumber("day", Integer.class);

    //inherited
    public final StringPath description = _super.description;

    public final NumberPath<Long> organizationId = createNumber("organizationId", Long.class);

    public final EnumPath<com.cagiris.coho.service.api.UserRole> userRole = createEnum("userRole", com.cagiris.coho.service.api.UserRole.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QAnnualHolidayEntity(String variable) {
        super(AnnualHolidayEntity.class, forVariable(variable));
    }

    public QAnnualHolidayEntity(Path<? extends AnnualHolidayEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAnnualHolidayEntity(PathMetadata<?> metadata) {
        super(AnnualHolidayEntity.class, metadata);
    }

}

