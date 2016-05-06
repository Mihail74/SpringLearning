/*
* (c) Сергей Киченко, 2016. Все права защищены.
 */
package ru.kichenko.sample.web.model.dto;

import com.googlecode.jmapper.annotations.JMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AbstractDto
 *
 * @author Сергей Киченко
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractDto {

    @JMap
    private Long id;
}
