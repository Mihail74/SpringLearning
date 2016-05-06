/*
 * (c) Payway, 2016. All right reserved.
 */
package ru.kichenko.sample.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kichenko.sample.web.service.StudentService;

/**
 * StudentController
 *
 * @author Sergey Kichenko
 * @created 05.05.2016
 */
@Slf4j
@Controller
@RequestMapping(value = "/students/show")
public class StudentShowController {

    @Autowired
    private StudentService service;

    @RequestMapping(method = RequestMethod.GET)
    public String show(@RequestParam final Long id, final ModelMap model) {
        model.addAttribute("student", service.findById(id));
        return "students";
    }

}
