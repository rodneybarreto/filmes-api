package br.com.rodneybarreto.moviesapi.adapter.in.web.dto;

import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CustomerRequest(
        @NotBlank(message = "The name is required")
        String name,

        @Email(message = "The email must be valid")
        @NotBlank(message = "The email is required")
        @Size(max = 255, message = "The email must be less than 255 characters")
        String email,

        @Size(max = 255, message = "The pix key must be less than 255 characters")
        @JsonAlias("pix_key")
        String pixKey
) {

    public static Customer toDomain(CustomerRequest customerRequest) {
        return new Customer(customerRequest.name(), customerRequest.email(), customerRequest.pixKey());
    }

}
