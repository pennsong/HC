package com.qtc.hospitalcore.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

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

        result.put("errors", errors);

        return result;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> on(HttpMessageNotReadableException e) {

        Map<String, Object> result = new HashMap<>();

        result.put("errors", e.getMessage());

        return result;
    }
}
