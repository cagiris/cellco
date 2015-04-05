/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.cagiris.coho.service.api.IBaseEntity;

/**
 *
 * @author: ssnk
 */
@MappedSuperclass
public abstract class BaseEntity implements IBaseEntity {
    private Date dateAdded;
    private Date dateModified;

    @Override
    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
