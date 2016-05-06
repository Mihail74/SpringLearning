/*
 * (c) Сергей Киченко, 2016. Все права защищены.
 */
package ru.kichenko.sample.web.service.impl;

import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kichenko.sample.web.model.Group;
import ru.kichenko.sample.web.model.Student;
import ru.kichenko.sample.web.service.StudentService;

/**
 * StudentServiceImpl
 *
 * @author Sergey Kichenko
 * @created 05.05.2016
 */
@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final static Map<Long, Student> map = Maps.newConcurrentMap();

    static {
        map.put(1L, new Student(1L, "Sergey", "Kichenko", new Group(1L, "Java developers")));
        map.put(2L, new Student(2L, "Ivan", "Plushkin", new Group(2L, "C# developers")));
        map.put(3L, new Student(3L, "Andrey", "Black", new Group(3L, "PHP developers")));
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Student findById(final Long id) {
        return map.get(id);
    }

    @Override
    public Student remove(Long id) {
        return map.remove(id);
    }
}
