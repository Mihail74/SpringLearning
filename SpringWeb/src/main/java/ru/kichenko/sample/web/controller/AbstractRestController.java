/*
 * (c) Сергей Киченко, 2016. Все права защищены.
 */
package ru.kichenko.sample.web.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.management.RuntimeErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kichenko.sample.web.core.Wrapper;

/**
 * AbstractController
 *
 * @author Сергей Киченко
 */
@Slf4j
@RestController
public abstract class AbstractRestController {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Wrapper<String> handleException(Exception ex) {

        log.error("Необработанная ошибка в контроллере", ex);

        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(new StringWriter());
        ex.printStackTrace(pw);

        return new Wrapper<>(false, sw.toString(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(RuntimeErrorException.class)
    public Wrapper<String> handleRuntimeException(Exception ex) {

        log.error("Необработанная runtime-ошибка в контроллере", ex);

        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(new StringWriter());
        ex.printStackTrace(pw);

        return new Wrapper<>(false, sw.toString(), ex.getMessage());
    }
}
