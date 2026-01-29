package br.com.rodneybarreto.moviesapi.infrastructure.handler;

import lombok.Builder;

import java.util.List;

@Builder
public record ErrorResponse(String error, List<ErrorDetails> details) {
}
