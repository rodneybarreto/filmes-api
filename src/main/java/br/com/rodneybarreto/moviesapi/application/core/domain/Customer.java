package br.com.rodneybarreto.moviesapi.application.core.domain;

import java.util.Objects;

public class Customer {

    private Long id;
    private String name;
    private String email;
    private String pixKey;

    public Customer() {}

    public Customer(Long id, String name, String email, String pixKey) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pixKey = pixKey;
    }

    public Customer(String name, String email, String pixKey) {
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
        Objects.requireNonNull(name, "Name cannot be null");
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Objects.requireNonNull(email, "Email cannot be null");
        this.email = email;
    }

    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format(
                """
                Customer{"id":%d,"name":"%s","email":"%s","pixKey":"%s"}
                """,
                id,
                name,
                email,
                pixKey
        );
    }

    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    public static class CustomerBuilder {

        private Long id;
        private String name;
        private String email;
        private String pixKey;

        private CustomerBuilder() {
        }

        public CustomerBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CustomerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CustomerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder pixKey(String pixKey) {
            this.pixKey = pixKey;
            return this;
        }

        public Customer build() {
            return new Customer(id, name, email, pixKey);
        }

    }

}
