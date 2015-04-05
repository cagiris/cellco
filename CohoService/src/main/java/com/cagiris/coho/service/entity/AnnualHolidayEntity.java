/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.cagiris.coho.service.api.IAnnualHoliday;
import com.cagiris.coho.service.api.UserRole;
import com.cagiris.coho.service.entity.AnnualHolidayEntity.AnnualHolidayPK;

/**
 *
 * @author: ssnk
 */
@Entity
@IdClass(AnnualHolidayPK.class)
public class AnnualHolidayEntity extends HolidayEntity implements IAnnualHoliday {

    private Integer year;

    // day of year
    private Integer day;

    @Id
    @Override
    public Long getOrganizationId() {
        return super.getOrganizationId();
    }

    @Id
    @Override
    public Integer getYear() {
        return this.year;
    }

    @Id
    @Override
    public Integer getDay() {
        return this.day;
    }

    @Id
    @Override
    public UserRole getUserRole() {
        return super.getUserRole();
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public static class AnnualHolidayPK implements Serializable {

        private Long organizationId;

        private UserRole userRole;

        private Integer year;

        private Integer day;

        public Long getOrganizationId() {
            return organizationId;
        }

        public void setOrganizationId(Long organizationId) {
            this.organizationId = organizationId;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public Integer getDay() {
            return day;
        }

        public void setDay(Integer day) {
            this.day = day;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((day == null) ? 0 : day.hashCode());
            result = prime * result + ((organizationId == null) ? 0 : organizationId.hashCode());
            result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
            result = prime * result + ((year == null) ? 0 : year.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            AnnualHolidayPK other = (AnnualHolidayPK)obj;
            if (day == null) {
                if (other.day != null)
                    return false;
            } else if (!day.equals(other.day))
                return false;
            if (organizationId == null) {
                if (other.organizationId != null)
                    return false;
            } else if (!organizationId.equals(other.organizationId))
                return false;
            if (userRole != other.userRole)
                return false;
            if (year == null) {
                if (other.year != null)
                    return false;
            } else if (!year.equals(other.year))
                return false;
            return true;
        }

        public UserRole getUserRole() {
            return userRole;
        }

        public void setUserRole(UserRole userRole) {
            this.userRole = userRole;
        }

    }
}
