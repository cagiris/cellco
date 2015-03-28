/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

/**
 *
 * @author: ssnk
 */

public interface ITeam {

	Long getTeamId();

	String getTeamName();

	String getTeamDescription();

	Long getOrganizationId();

	Long getParentTeamId();

}
