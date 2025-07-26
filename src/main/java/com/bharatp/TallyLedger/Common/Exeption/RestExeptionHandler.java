package com.bharatp.TallyLedger.Common.Exeption;

import com.bharatp.TallyLedger.Common.Defines.CustomHTTPStatus;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RestExeptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleValidation(MethodArgumentNotValidException exception)
    {
        List<String> error = exception.getBindingResult().getFieldErrors().stream().map(
                fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage()).toList();
        return new ErrorResponseDTO(CustomHTTPStatus.BAD_REQUEST, "Validation Failed", null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleConstraintViolation(ConstraintViolationException exception)
    {
        List<String> errors = exception.getConstraintViolations().stream().
                map(constraintViolation -> constraintViolation.getPropertyPath() +": " + constraintViolation.getMessage() ).toList();
        return new ErrorResponseDTO(CustomHTTPStatus.BAD_REQUEST,"Validation Failed", errors);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleNotFound(EntityNotFoundException ex) {
        return new ErrorResponseDTO(CustomHTTPStatus.NOT_FOUND, ex.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleGeneral(Exception ex) {
        return new ErrorResponseDTO(CustomHTTPStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", List.of(ex.getMessage()));
    }


}
