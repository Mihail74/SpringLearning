/*
 * (c) Payway, 2016. All right reserved.
 */
package ru.kichenko.sample.web.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NotFoundException
 *
 * @author Sergey Kichenko
 * @created 05.05.2016
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Something not found")
public class NotFoundException extends Exception {

}
