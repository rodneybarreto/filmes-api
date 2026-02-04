package br.com.rodneybarreto.moviesapi.adapter.in.web.dto;

import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CustomerResponse(
        long id,

        String name,

        String email,

        @JsonProperty("pix_key")
        String pixKey
) {

    public CustomerResponse(Customer customer) {
        this(customer.getId(), customer.getName(), customer.getEmail(), customer.getPixKey());
    }

}
