/*
* (c) Сергей Киченко, 2016. Все права защищены.
 */
package ru.kichenko.sample.web.model.dto;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * StudentDto
 *
 * @author Сергей Киченко
 */
@Getter
@Setter
@NoArgsConstructor
public class StudentDto extends AbstractDto {

    @JMap
    private String name;

    @JMap
    private String surname;

    @JMap
    private GroupDto group;
}
