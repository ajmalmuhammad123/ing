package com.ing.bank.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author ajmal
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String notFoundException(final Exception e) {
        e.printStackTrace();
        return "error";
    }

}
