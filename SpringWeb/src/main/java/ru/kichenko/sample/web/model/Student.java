/*
* (c) Сергей Киченко, 2016. Все права защищены.
 */
package ru.kichenko.sample.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Student
 *
 * @author Сергей Киченко
 */
@Getter
@Setter
@NoArgsConstructor
public class Student extends AbstractEntity {

    private String name;
    private String surname;
    private Group group;

    public Student(final Long id, final String name, final String surname, final Group group) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.group = group;
    }
}