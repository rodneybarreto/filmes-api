package br.com.rodneybarreto.moviesapi.adapter.out.persistence.entity;

import br.com.rodneybarreto.moviesapi.adapter.out.persistence.converter.CryptoConverter;
import br.com.rodneybarreto.moviesapi.application.core.domain.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
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

    public CustomerJpaEntity() {
    }

    public CustomerJpaEntity(Long id, String name, String email, String pixKey) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pixKey = pixKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    public static CustomerJpaEntity.CustomerJpaEntityBuilder builder() {
        return new CustomerJpaEntity.CustomerJpaEntityBuilder();
    }

    public static class CustomerJpaEntityBuilder {

        private Long id;
        private String name;
        private String email;
        private String pixKey;

        private CustomerJpaEntityBuilder() {
        }

        public CustomerJpaEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CustomerJpaEntityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CustomerJpaEntityBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CustomerJpaEntityBuilder pixKey(String pixKey) {
            this.pixKey = pixKey;
            return this;
        }

        public CustomerJpaEntity build() {
            return new CustomerJpaEntity(id, name, email, pixKey);
        }
    }

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
