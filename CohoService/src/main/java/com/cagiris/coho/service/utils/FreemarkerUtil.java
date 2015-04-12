/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.utils;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cagiris.coho.service.exception.CohoException;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 *
 * @author: ssnk
 */

public class FreemarkerUtil {
    private Configuration configuration;

    private static Logger logger = LoggerFactory.getLogger(FreemarkerUtil.class);

    public FreemarkerUtil(List<String> templateNames) {
        this.configuration = new Configuration();
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        for (String templateName : templateNames) {
            URL url = Resources.getResource(templateName);
            String templateString;
            try {
                templateString = Resources.toString(url, Charsets.UTF_8);
                stringTemplateLoader.putTemplate(templateName, templateString);
            } catch (IOException e) {
                logger.error("Failed to load template:{}", templateName, e);
            }
        }
        configuration.setTemplateLoader(stringTemplateLoader);
    }

    public String evaluateTemplate(String templateName, Object contextObject) throws CohoException {
        try {
            Template template = configuration.getTemplate(templateName);
            Map<String, Object> contextMap = new HashMap<String, Object>();
            contextMap.put("contextObject", contextObject);
            CharArrayWriter charArrayWriter = new CharArrayWriter();
            template.process(contextMap, charArrayWriter);
            return charArrayWriter.toString();
        } catch (IOException e) {
            throw new CohoException(e);
        } catch (TemplateException e) {
            throw new CohoException(e);
        }
    }

}
