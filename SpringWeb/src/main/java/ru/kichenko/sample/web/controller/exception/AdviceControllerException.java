/*
 * (c) Payway, 2016. All right reserved.
 */
package ru.kichenko.sample.web.controller.exception;

import lombok.NoArgsConstructor;

/**
 * AdviceControllerException
 *
 * @author Sergey Kichenko
 * @created 05.05.2016
 */
@NoArgsConstructor
public class AdviceControllerException extends Exception {

    public AdviceControllerException(String message) {
        super(message);
    }
}
