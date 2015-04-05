/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.cagiris.coho.service.api.IWeeklyHoliday;
import com.cagiris.coho.service.api.UserRole;
import com.cagiris.coho.service.entity.WeeklyHolidayEntity.WeeklyHolidayPK;

/**
 *
 * @author: ssnk
 */
@Entity
@IdClass(WeeklyHolidayPK.class)
public class WeeklyHolidayEntity extends HolidayEntity implements IWeeklyHoliday {

    private Integer weekDay;

    @Id
    @Override
    public Long getOrganizationId() {
        return super.getOrganizationId();
    }

    @Id
    @Override
    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;

    }

    @Id
    @Override
    public UserRole getUserRole() {
        return super.getUserRole();
    }

    public static class WeeklyHolidayPK implements Serializable {
        private Long organizationId;
        private UserRole userRole;
        private Integer weekDay;

        public Long getOrganizationId() {
            return organizationId;
        }

        public void setOrganizationId(Long organizationId) {
            this.organizationId = organizationId;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((organizationId == null) ? 0 : organizationId.hashCode());
            result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
            result = prime * result + ((weekDay == null) ? 0 : weekDay.hashCode());
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
            WeeklyHolidayPK other = (WeeklyHolidayPK)obj;
            if (organizationId == null) {
                if (other.organizationId != null)
                    return false;
            } else if (!organizationId.equals(other.organizationId))
                return false;
            if (userRole != other.userRole)
                return false;
            if (weekDay == null) {
                if (other.weekDay != null)
                    return false;
            } else if (!weekDay.equals(other.weekDay))
                return false;
            return true;
        }

        public Integer getWeekDay() {
            return weekDay;
        }

        public void setWeekDay(Integer weekDay) {
            this.weekDay = weekDay;
        }

        public UserRole getUserRole() {
            return userRole;
        }

        public void setUserRole(UserRole userRole) {
            this.userRole = userRole;
        }
    }

}
