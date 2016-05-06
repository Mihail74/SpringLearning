/*
 * (c) Payway, 2016. All right reserved.
 */
package ru.kichenko.sample.web.controller.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.kichenko.sample.web.controller.exception.AdviceControllerException;

/**
 * AdviceController
 *
 * @author Sergey Kichenko
 * @created 05.05.2016
 */
@Slf4j
@ControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AdviceControllerException.class)
    public ModelAndView exception(final AdviceControllerException e) {

        final ModelAndView mav = new ModelAndView("error");
        mav.addObject("name", e.getClass().getSimpleName());
        mav.addObject("message", e.getMessage());

        return mav;
    }
}
