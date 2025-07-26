package com.bharatp.TallyLedger.Common.Exeption;

import java.util.List;

public class ErrorResponseDTO {
    private int status;
    private String message;
    private List<String> errors;

    public ErrorResponseDTO(int status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
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
