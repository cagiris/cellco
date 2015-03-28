package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTeamEntity is a Querydsl query type for TeamEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTeamEntity extends EntityPathBase<TeamEntity> {

    private static final long serialVersionUID = -1311640874L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTeamEntity teamEntity = new QTeamEntity("teamEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> dateAdded = _super.dateAdded;

    //inherited
    public final DateTimePath<java.util.Date> dateModified = _super.dateModified;

    public final QOrganizationEntity organizationEntity;

    public final QTeamEntity parentTeamEntity;

    public final StringPath teamDescription = createString("teamDescription");

    public final NumberPath<Long> teamId = createNumber("teamId", Long.class);

    public final StringPath teamName = createString("teamName");

    public QTeamEntity(String variable) {
        this(TeamEntity.class, forVariable(variable), INITS);
    }

    public QTeamEntity(Path<? extends TeamEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTeamEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTeamEntity(PathMetadata<?> metadata, PathInits inits) {
        this(TeamEntity.class, metadata, inits);
    }

    public QTeamEntity(Class<? extends TeamEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.organizationEntity = inits.isInitialized("organizationEntity") ? new QOrganizationEntity(forProperty("organizationEntity")) : null;
        this.parentTeamEntity = inits.isInitialized("parentTeamEntity") ? new QTeamEntity(forProperty("parentTeamEntity"), inits.get("parentTeamEntity")) : null;
    }

}

