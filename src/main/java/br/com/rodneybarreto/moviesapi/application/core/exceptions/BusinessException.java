package br.com.rodneybarreto.moviesapi.application.core.exceptions;

public class BusinessException extends RuntimeException {

    private static ApiError apiError;

    public BusinessException(ApiError apiError) {
        super(apiError.getMessage());
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public static void of(ApiError error) {
        apiError = error;
    }

}
