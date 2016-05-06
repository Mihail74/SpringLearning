/*
 * (c) Сергей Киченко, 2016. Все права защищены.
 */
package ru.kichenko.sample.web.service;

import java.util.List;
import ru.kichenko.sample.web.model.Student;

/**
 * StudentService
 *
 * @author Сергей Киченко
 */
public interface StudentService {

    List<Student> findAll();

    Student findById(final Long id);

    Student remove(final Long id);
}
