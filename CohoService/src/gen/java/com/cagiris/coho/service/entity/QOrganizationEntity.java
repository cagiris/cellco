package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QOrganizationEntity is a Querydsl query type for OrganizationEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrganizationEntity extends EntityPathBase<OrganizationEntity> {

    private static final long serialVersionUID = 1344540460L;

    public static final QOrganizationEntity organizationEntity = new QOrganizationEntity("organizationEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> dateAdded = _super.dateAdded;

    //inherited
    public final DateTimePath<java.util.Date> dateModified = _super.dateModified;

    public final StringPath organizationDescription = createString("organizationDescription");

    public final NumberPath<Long> organizationId = createNumber("organizationId", Long.class);

    public final StringPath organizationName = createString("organizationName");

    public QOrganizationEntity(String variable) {
        super(OrganizationEntity.class, forVariable(variable));
    }

    public QOrganizationEntity(Path<? extends OrganizationEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrganizationEntity(PathMetadata<?> metadata) {
        super(OrganizationEntity.class, metadata);
    }

}

