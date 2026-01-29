package br.com.rodneybarreto.moviesapi.application.core.exception;

public enum ApiError {

    COMUNICATION_ERROR(408, "Comunication error", "Comunication error between services"),
    INTERNAL_ERROR(500, "Internal error", "Unespected error found"),
    INVALID_FIELD_ERROR(452, "Invalid field error", "Invalid field error found");

    private final int code;
    private final String error;
    private final String message;

    ApiError(int code, String error, String message) {
        this.code = code;
        this.error = error;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

}
