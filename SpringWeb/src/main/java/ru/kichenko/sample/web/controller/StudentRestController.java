/*
 * (c) Сергей Киченко, 2016. Все права защищены.
 */
package ru.kichenko.sample.web.controller;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.googlecode.jmapper.JMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.kichenko.sample.web.core.Wrapper;
import ru.kichenko.sample.web.model.Student;
import ru.kichenko.sample.web.model.dto.StudentDto;
import ru.kichenko.sample.web.service.StudentService;

/**
 * StudentController
 *
 * @author Sergey Kichenko
 * @created 05.05.2016
 */
@Slf4j
@RestController
@RequestMapping(value = "/rest/students")
public class StudentRestController extends AbstractRestController {

    @Autowired
    private JMapper<StudentDto, Student> mapper;

    @Autowired
    private StudentService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<StudentDto> findAll() {
        return Lists.transform(service.findAll(), new Function<Student, StudentDto>() {
            @Override
            public StudentDto apply(final Student src) {
                return mapper.getDestination(src);
            }
        });
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Wrapper<StudentDto> findById(@PathVariable final Long id) {
        return new Wrapper<>(mapper.getDestination(service.findById(id)));
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public Wrapper<StudentDto> removeById(@PathVariable final Long id) {
        service.remove(id);
        return new Wrapper<>();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Wrapper<StudentDto> create(@RequestBody final StudentDto student) {
        //TODO: insert here...
        return new Wrapper<>();
    }
}
