package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTeamShiftDetailsEntity is a Querydsl query type for TeamShiftDetailsEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTeamShiftDetailsEntity extends EntityPathBase<TeamShiftDetailsEntity> {

    private static final long serialVersionUID = 1829857750L;

    public static final QTeamShiftDetailsEntity teamShiftDetailsEntity = new QTeamShiftDetailsEntity("teamShiftDetailsEntity");

    public final NumberPath<Long> shiftEndTime = createNumber("shiftEndTime", Long.class);

    public final NumberPath<Long> shiftStartTime = createNumber("shiftStartTime", Long.class);

    public final NumberPath<Long> teamId = createNumber("teamId", Long.class);

    public QTeamShiftDetailsEntity(String variable) {
        super(TeamShiftDetailsEntity.class, forVariable(variable));
    }

    public QTeamShiftDetailsEntity(Path<? extends TeamShiftDetailsEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeamShiftDetailsEntity(PathMetadata<?> metadata) {
        super(TeamShiftDetailsEntity.class, metadata);
    }

}

