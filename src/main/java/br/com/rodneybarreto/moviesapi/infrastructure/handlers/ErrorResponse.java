package br.com.rodneybarreto.moviesapi.infrastructure.handlers;

import lombok.Builder;

import java.util.List;

@Builder
public record ErrorResponse(String error, List<ErrorDetails> details) {
}
