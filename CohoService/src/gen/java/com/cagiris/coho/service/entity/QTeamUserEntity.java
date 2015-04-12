package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTeamUserEntity is a Querydsl query type for TeamUserEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTeamUserEntity extends EntityPathBase<TeamUserEntity> {

    private static final long serialVersionUID = 1586222337L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTeamUserEntity teamUserEntity = new QTeamUserEntity("teamUserEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> dateAdded = _super.dateAdded;

    //inherited
    public final DateTimePath<java.util.Date> dateModified = _super.dateModified;

    public final QTeamEntity teamEntity;

    public final NumberPath<Long> teamUserId = createNumber("teamUserId", Long.class);

    public final QUserEntity userEntity;

    public QTeamUserEntity(String variable) {
        this(TeamUserEntity.class, forVariable(variable), INITS);
    }

    public QTeamUserEntity(Path<? extends TeamUserEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTeamUserEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTeamUserEntity(PathMetadata<?> metadata, PathInits inits) {
        this(TeamUserEntity.class, metadata, inits);
    }

    public QTeamUserEntity(Class<? extends TeamUserEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.teamEntity = inits.isInitialized("teamEntity") ? new QTeamEntity(forProperty("teamEntity"), inits.get("teamEntity")) : null;
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity"), inits.get("userEntity")) : null;
    }

}

