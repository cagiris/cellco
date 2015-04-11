package com.cagiris.coho.service.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCustomerEntity is a Querydsl query type for CustomerEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCustomerEntity extends EntityPathBase<CustomerEntity> {

    private static final long serialVersionUID = 966475927L;

    public static final QCustomerEntity customerEntity = new QCustomerEntity("customerEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath billingAddress = createString("billingAddress");

    public final StringPath contactNumber = createString("contactNumber");

    public final NumberPath<Long> customerId = createNumber("customerId", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> dateAdded = _super.dateAdded;

    //inherited
    public final DateTimePath<java.util.Date> dateModified = _super.dateModified;

    public final StringPath emailId = createString("emailId");

    public final StringPath firstName = createString("firstName");

    public final StringPath lastName = createString("lastName");

    public final StringPath middleName = createString("middleName");

    public QCustomerEntity(String variable) {
        super(CustomerEntity.class, forVariable(variable));
    }

    public QCustomerEntity(Path<? extends CustomerEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomerEntity(PathMetadata<?> metadata) {
        super(CustomerEntity.class, metadata);
    }

}

