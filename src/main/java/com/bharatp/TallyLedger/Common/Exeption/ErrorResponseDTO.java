package com.bharatp.TallyLedger.Common.Exeption;

import org.springframework.web.ErrorResponse;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponseDTO {
    private int status;
    private int errorCode;
    private String error;
    private LocalDateTime timeStamp;
    private String message;
    private List<String> errors;
    private final String path;

    private ErrorResponseDTO(int status, int errorCode, String error, LocalDateTime timeStamp, String message, List<String> errors, String path) {
        this.status = status;
        this.errorCode = errorCode;
        this.error = error;
        this.timeStamp = timeStamp;
        this.message = message;
        this.errors = errors;
        this.path = path;
    }
    public static class ErrorResponseBuilder
    {
        private int status;
        private int errorCode;
        private String error;
        private LocalDateTime timeStamp;
        private String message;
        private List<String> errors;
        private String path;

        public ErrorResponseBuilder setPath(String path) {
            this.path = path;
            return this;
        }

        public ErrorResponseBuilder setStatus(int status) {
            this.status = status;
            return this;
        }

        public ErrorResponseBuilder setErrorCode(int errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ErrorResponseBuilder setError(String error) {
            this.error = error;
            return this;
        }

        public ErrorResponseBuilder setTimeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public ErrorResponseBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseBuilder setErrors(List<String> errors) {
            this.errors = errors;
            return this;
        }

        public ErrorResponseDTO create()
        {
            return new ErrorResponseDTO(status,errorCode,error,timeStamp,message,errors, path);
        }
    }
    public int getErrorCode() {
        return errorCode;
    }

    public String getError() {
        return error;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
