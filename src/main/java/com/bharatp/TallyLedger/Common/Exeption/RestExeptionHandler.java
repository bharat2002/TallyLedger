package com.bharatp.TallyLedger.Common.Exeption;

import com.bharatp.TallyLedger.Common.Defines.CustomHTTPStatus;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExeptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleValidation(MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        return new ErrorResponseDTO.ErrorResponseBuilder()
                .setTimeStamp((LocalDateTime.now()))
                .setStatus(CustomHTTPStatus.BAD_REQUEST)
                .setError(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .setMessage("Validation failed")
                .setPath(request.getRequestURI())
                .setErrors(errors)
                .create();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleConstraintViolation(ConstraintViolationException exception, HttpServletRequest request) {
        List<String> errors = exception.getConstraintViolations()
                .stream()
                .map(cv -> {
                    String param = cv.getPropertyPath().toString();
                    if (param.contains(".")) {
                        param = param.substring(param.lastIndexOf('.') + 1);
                    }
                    return param + ": " + cv.getMessage();
                })
                .collect(Collectors.toList());

        return new ErrorResponseDTO.ErrorResponseBuilder()
                .setTimeStamp((LocalDateTime.now()))
                .setStatus(CustomHTTPStatus.BAD_REQUEST)
                .setError(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .setMessage("Constraint violation")
                .setPath(request.getRequestURI())
                .setErrors(errors)
                .create();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        return new ErrorResponseDTO.ErrorResponseBuilder()
                .setTimeStamp((LocalDateTime.now()))
                .setStatus(CustomHTTPStatus.NOT_FOUND)
                .setError(HttpStatus.NOT_FOUND.getReasonPhrase())
                .setMessage(ex.getMessage())
                .setPath(request.getRequestURI())
                .create();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleNotFoundException(NotFoundException ex, HttpServletRequest request)
    {
        return new ErrorResponseDTO.ErrorResponseBuilder()
                .setTimeStamp((LocalDateTime.now()))
                .setStatus(CustomHTTPStatus.NOT_FOUND)
                .setError(HttpStatus.NOT_FOUND.getReasonPhrase())
                .setMessage("Not Found Error")
                .setPath(request.getRequestURI())
                .setErrors(List.of(ex.getMessage()))
                .create();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handleGeneral(Exception ex, HttpServletRequest request)
    {
        return new ErrorResponseDTO.ErrorResponseBuilder()
                .setTimeStamp((LocalDateTime.now()))
                .setStatus(CustomHTTPStatus.INTERNAL_SERVER_ERROR)
                .setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .setMessage("Internal Server Error")
                .setPath(request.getRequestURI())
                .setErrors(List.of(ex.getMessage()))
                .create();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDTO handleDuplicateEntry(DataIntegrityViolationException ex, HttpServletRequest request)
    {
        String message = "Duplicate entry. Entry with the same name may already exist.";
        return new ErrorResponseDTO.ErrorResponseBuilder()
                .setTimeStamp(LocalDateTime.now())
                .setStatus(HttpStatus.CONFLICT.value())
                .setError(HttpStatus.CONFLICT.getReasonPhrase())
                .setMessage(message)
                .setPath(request.getRequestURI())
                .setErrors(List.of(message))
                .create();
    }


}
