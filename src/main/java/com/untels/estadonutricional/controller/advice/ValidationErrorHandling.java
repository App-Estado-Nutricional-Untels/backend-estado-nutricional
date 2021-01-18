package com.untels.estadonutricional.controller.advice;

import com.untels.estadonutricional.dto.response.Error;
import com.untels.estadonutricional.dto.response.RespuestaError;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ValidationErrorHandling {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    RespuestaError onConstraintValidationException(
            ConstraintViolationException e
    ) {
        List<Error> errores = new ArrayList<>();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            String[] paths = violation.getPropertyPath().toString().split("\\.");

            errores.add(new Error(paths[paths.length - 1],
                    violation.getMessage()));
        }

        return new RespuestaError(errores);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    RespuestaError onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<Error> errores = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errores.add(new Error(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return new RespuestaError(errores);
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    RespuestaError handleBindException(
            BindException e
    ) {
        List<Error> errores = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errores.add(new Error(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return new RespuestaError(errores);
    }
}
