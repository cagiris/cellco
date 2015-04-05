package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrganizationMetaConfigurationEntity is a Querydsl query type for OrganizationMetaConfigurationEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrganizationMetaConfigurationEntity extends EntityPathBase<OrganizationMetaConfigurationEntity> {

    private static final long serialVersionUID = 17892875L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrganizationMetaConfigurationEntity organizationMetaConfigurationEntity = new QOrganizationMetaConfigurationEntity("organizationMetaConfigurationEntity");

    public final SetPath<com.cagiris.coho.service.api.UserRole, EnumPath<com.cagiris.coho.service.api.UserRole>> availableUserRoles = this.<com.cagiris.coho.service.api.UserRole, EnumPath<com.cagiris.coho.service.api.UserRole>>createSet("availableUserRoles", com.cagiris.coho.service.api.UserRole.class, EnumPath.class, PathInits.DIRECT2);

    public final EnumPath<com.cagiris.coho.service.api.AuthenicationPolicy> defaultAuthenticationPolicy = createEnum("defaultAuthenticationPolicy", com.cagiris.coho.service.api.AuthenicationPolicy.class);

    public final NumberPath<Integer> hierarchyDepth = createNumber("hierarchyDepth", Integer.class);

    public final NumberPath<Integer> maxTeamsAllowed = createNumber("maxTeamsAllowed", Integer.class);

    public final NumberPath<Integer> maxUsersAllowed = createNumber("maxUsersAllowed", Integer.class);

    public final QOrganizationEntity organizationEntity;

    public final NumberPath<Long> organizationId = createNumber("organizationId", Long.class);

    public final StringPath orgPrefix = createString("orgPrefix");

    public final EnumPath<com.cagiris.coho.service.api.UserIdGenerationPolicy> userIdGenerationPolicy = createEnum("userIdGenerationPolicy", com.cagiris.coho.service.api.UserIdGenerationPolicy.class);

    public QOrganizationMetaConfigurationEntity(String variable) {
        this(OrganizationMetaConfigurationEntity.class, forVariable(variable), INITS);
    }

    public QOrganizationMetaConfigurationEntity(Path<? extends OrganizationMetaConfigurationEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrganizationMetaConfigurationEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrganizationMetaConfigurationEntity(PathMetadata<?> metadata, PathInits inits) {
        this(OrganizationMetaConfigurationEntity.class, metadata, inits);
    }

    public QOrganizationMetaConfigurationEntity(Class<? extends OrganizationMetaConfigurationEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.organizationEntity = inits.isInitialized("organizationEntity") ? new QOrganizationEntity(forProperty("organizationEntity")) : null;
    }

}

