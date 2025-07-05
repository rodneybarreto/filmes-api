package br.com.rodneybarreto.moviesapi.application.core.exceptions;

public enum ApiError {

    COMUNICATION_ERROR(408, "Comunication error", "Comunication error between services"),
    INTERNAL_ERROR(500, "Internal error", "Unespected error found"),
    INVALID_FIELD_ERROR(452, "Invalid field error", "Invalid field error found");

    private final int code;
    private final String message;
    private final String description;

    ApiError(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

}
