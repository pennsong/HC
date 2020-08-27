package com.qtc.hospitalcore.api;

import com.qtc.hospitalcore.domain.exception.PPBusinessException;
import com.qtc.hospitalcore.domain.exception.PPException;
import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> on(MethodArgumentNotValidException e) {
        Map<String, Object> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().stream().forEach(
                item -> errors.put(((FieldError)item).getField(), item.getDefaultMessage())
        );

        Map<String, Object> result = new HashMap<>();

        result.put("code", "-1");
        result.put("errorType", e.getClass().getName());
        result.put("errors", errors);

        return result;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class,
            PPException.class,
            CommandExecutionException.class,
            NoSuchElementException.class,
            NoResultException.class
    })
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> on(Exception e) {

        Map<String, Object> result = new HashMap<>();

        result.put("code", "-1");
        result.put("errorType", e.getClass().getName());
        result.put("errors", e.getMessage());

        return result;
    }
}
