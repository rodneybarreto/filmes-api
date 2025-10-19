package br.com.rodneybarreto.moviesapi.adapters.mapper;

import br.com.rodneybarreto.moviesapi.adapters.inbound.dto.CustomerRequest;
import br.com.rodneybarreto.moviesapi.adapters.inbound.dto.CustomerResponse;
import br.com.rodneybarreto.moviesapi.adapters.outbound.database.entity.CustomerEntity;
import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CustomerMapper {
    
    public Customer toDomain(CustomerRequest CustomerRequest) {
        return Customer.builder()
                .name(CustomerRequest.name())
                .email(CustomerRequest.email())
                .pixKey(CustomerRequest.pixKey())
                .build();
    }

    public Customer toDomain(CustomerEntity customerEntity) {
        return Customer.builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .email(customerEntity.getEmail())
                .pixKey(customerEntity.getPixKey())
                .build();
    }

    public CustomerEntity toEntity(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customer.getName());
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setPixKey(customer.getPixKey());
        return customerEntity;
    }

    public CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPixKey()
        );
    }

}
