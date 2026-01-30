package br.com.rodneybarreto.moviesapi.application.core.exception;

public enum ApiError {

    COMUNICATION_ERROR("CE408", "Comunication error", "Comunication error between services"),
    INTERNAL_ERROR("IE500", "Internal error", "Unespected error found"),
    INVALID_FIELD_ERROR("IFE452", "Invalid field error", "Invalid field error found");

    private final String code;
    private final String error;
    private final String message;

    ApiError(String code, String error, String message) {
        this.code = code;
        this.error = error;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

}
