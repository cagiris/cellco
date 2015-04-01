/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.db.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.DefaultNamingStrategy;

/**
 *
 * @author: ssnk
 */

public class CohoDBNamingStratergy extends DefaultNamingStrategy {
    @Override
    public String tableName(String tableName) {
        return splitName(tableName);
    }

    @Override
    public String columnName(String tableName) {
        return splitName(tableName);
    }

    String splitName(String name) {
        int lastIndex = 0;
        boolean isLastUppar = Character.isUpperCase(name.charAt(0));
        List<String> splits = new ArrayList<String>();
        for (int i = 0; i < name.length(); i++) {
            if (Character.isUpperCase(name.charAt(i)) && i - lastIndex > 0 && !isLastUppar) {
                splits.add(name.substring(lastIndex, i));
                lastIndex = i;
            }
            isLastUppar = Character.isUpperCase(name.charAt(i));
        }
        splits.add(name.substring(lastIndex));
        StringBuffer outName = new StringBuffer();
        boolean isFirst = true;
        for (String split : splits) {
            if (isFirst) {
                isFirst = false;
            } else {
                outName.append("_");
            }
            outName.append(split.toLowerCase());
        }
        return outName.toString();
    }

    public static void main(String[] args) {
        CohoDBNamingStratergy cohoDBNamingStratergy = new CohoDBNamingStratergy();
        System.out.println(cohoDBNamingStratergy.splitName("SomeCamelCaseClass"));
        System.out.println(cohoDBNamingStratergy.splitName("someCamelCaseField"));
        System.out.println(cohoDBNamingStratergy.splitName("SomeCCCaseClass"));
    }
}
