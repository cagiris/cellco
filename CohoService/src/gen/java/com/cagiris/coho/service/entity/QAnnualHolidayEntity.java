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

    //inherited
    public final StringPath description = _super.description;

    public final DateTimePath<java.util.Date> holidayDate = createDateTime("holidayDate", java.util.Date.class);

    public final NumberPath<Long> holidayId = createNumber("holidayId", Long.class);

    //inherited
    public final NumberPath<Long> organizationId = _super.organizationId;

    //inherited
    public final EnumPath<com.cagiris.coho.service.api.UserRole> userRole = _super.userRole;

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

