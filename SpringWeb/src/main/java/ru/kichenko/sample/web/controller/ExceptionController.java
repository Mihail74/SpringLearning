/*
 * (c) Payway, 2016. All right reserved.
 */
package ru.kichenko.sample.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kichenko.sample.web.controller.exception.AdviceControllerException;
import ru.kichenko.sample.web.controller.exception.NotFoundException;

/**
 * ExceptionController
 *
 * @author Sergey Kichenko
 * @created 05.05.2016
 */
@Slf4j
@Controller
@RequestMapping(value = "/exceptions")
public class ExceptionController {

    @RequestMapping(value = "/advice", method = RequestMethod.GET)
    public void adviceControllerException() throws AdviceControllerException {
        throw new AdviceControllerException("Advice exception");
    }

    @RequestMapping(value = "/not-found", method = RequestMethod.GET)
    public void notFoundException() throws NotFoundException {
        throw new NotFoundException();
    }
}
