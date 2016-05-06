/*
* (c) Сергей Киченко, 2016. Все права защищены.
 */
package ru.kichenko.sample.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Group
 *
 * @author Сергей Киченко
 */
@Getter
@Setter
@NoArgsConstructor
public class Group extends AbstractEntity {

    private String name;

    public Group(final Long id, final String name) {
        super(id);
        this.name = name;
    }
}
