/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author: ssnk
 */
@Controller
@RequestMapping
public interface ICRUDController<T> extends IController {
    @RequestMapping(method = RequestMethod.POST)
    ModelAndView create(@ModelAttribute T bean, BindingResult bindingResult, ModelMap modelMap);

    @RequestMapping(method = RequestMethod.PUT)
    ModelAndView update(@ModelAttribute T bean, BindingResult bindingResult, ModelMap modelMap);

    @RequestMapping(method = RequestMethod.DELETE)
    ModelAndView delete(String id);

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView get(String id);

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    ModelAndView getAll();

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    ModelAndView query(@RequestParam Map<String, String> queryParameters, ModelMap modelMap);

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView showDetailPage(Model model);
}
