package br.com.rodneybarreto.moviesapi.application.core.exception;

public class BusinessException extends RuntimeException {

    private ApiError apiError;

    public BusinessException(ApiError apiError) {
        this.apiError = apiError;
    }

    public static BusinessException of(ApiError apiError) {
        return new BusinessException(apiError);
    }

    public ApiError getError() {
        return this.apiError;
    }

}
