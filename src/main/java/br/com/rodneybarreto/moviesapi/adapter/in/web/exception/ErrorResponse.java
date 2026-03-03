package br.com.rodneybarreto.moviesapi.adapter.in.web.exception;

import java.util.List;

public record ErrorResponse(String code, String error, String message, List<String> messages) {

    public ErrorResponse(String error, String message) {
        this(null, error, message, null);
    }

    public ErrorResponse(String error, List<String> messages) {
        this(null, error, null, messages);
    }

    public static ErrorResponse.ErrorResponseBuilder builder() {
        return new ErrorResponse.ErrorResponseBuilder();
    }

    public static class ErrorResponseBuilder {

        private String code;
        private String error;
        private String message;
        private List<String> messages;

        private ErrorResponseBuilder() {
        }

        public ErrorResponseBuilder code(String code) {
            this.code = code;
            return this;
        }

        public ErrorResponseBuilder error(String error) {
            this.error = error;
            return this;
        }

        public ErrorResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseBuilder messages(List<String> messages) {
            this.messages = messages;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(code, error, message, messages);
        }
    }

}
