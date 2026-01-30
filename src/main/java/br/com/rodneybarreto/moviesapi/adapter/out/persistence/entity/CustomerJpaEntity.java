package br.com.rodneybarreto.moviesapi.adapter.out.persistence.entity;

import br.com.rodneybarreto.moviesapi.adapter.out.persistence.converter.CryptoConverter;
import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class CustomerJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "pix_key")
    @Convert(converter = CryptoConverter.class)
    private String pixKey;

    public static CustomerJpaEntity of(Customer customer) {
        return CustomerJpaEntity.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .pixKey(customer.getPixKey())
                .build();
    }

    public static Customer toDomain(CustomerJpaEntity customerJpaEntity) {
        return Customer.builder()
                .id(customerJpaEntity.getId())
                .name(customerJpaEntity.getName())
                .email(customerJpaEntity.getEmail())
                .pixKey(customerJpaEntity.getPixKey())
                .build();
    }

}
