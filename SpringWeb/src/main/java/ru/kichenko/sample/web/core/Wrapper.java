/*
 * (c) Сергей Киченко, 2016. Все права защищены.
 */
package ru.kichenko.sample.web.core;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Wrapper
 *
 * @author Sergey Kichenko
 * @created 05.05.2016
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wrapper<T> {

    private boolean success;

    @JsonSerialize
    private T data;

    @JsonSerialize
    private String message;

    public Wrapper(T data) {
        this(true, data, null);
    }

    public Wrapper(T data, boolean success) {
        this.data = data;
        success = true;
    }
}
