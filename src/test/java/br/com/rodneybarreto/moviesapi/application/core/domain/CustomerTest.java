package br.com.rodneybarreto.moviesapi.application.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CustomerTest {

    @Test
    @DisplayName("Should create customer using all args constructor")
    void shouldCreateCustomerWithAllArgsConstructor() {
        Long id = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";
        String pixKey = "12345678900";

        Customer customer = new Customer(id, name, email, pixKey);

        assertThat(customer.getId()).isEqualTo(id);
        assertThat(customer.getName()).isEqualTo(name);
        assertThat(customer.getEmail()).isEqualTo(email);
        assertThat(customer.getPixKey()).isEqualTo(pixKey);
    }

    @Test
    @DisplayName("Should create customer using constructor without ID")
    void shouldCreateCustomerWithoutIdConstructor() {
        String name = "Jane Doe";
        String email = "jane.doe@example.com";
        String pixKey = "09876543211";

        Customer customer = new Customer(name, email, pixKey);

        assertThat(customer.getId()).isNull();
        assertThat(customer.getName()).isEqualTo(name);
        assertThat(customer.getEmail()).isEqualTo(email);
        assertThat(customer.getPixKey()).isEqualTo(pixKey);
    }

    @Test
    @DisplayName("Should create customer using builder")
    void shouldCreateCustomerWithBuilder() {
        Long id = 2L;
        String name = "Alice Smith";
        String email = "alice.smith@example.com";
        String pixKey = "alice@pix";

        Customer customer = Customer.builder()
                .id(id)
                .name(name)
                .email(email)
                .pixKey(pixKey)
                .build();

        assertThat(customer.getId()).isEqualTo(id);
        assertThat(customer.getName()).isEqualTo(name);
        assertThat(customer.getEmail()).isEqualTo(email);
        assertThat(customer.getPixKey()).isEqualTo(pixKey);
    }

    @Test
    @DisplayName("Should update fields using setters")
    void shouldUpdateFieldsUsingSetters() {
        Customer customer = new Customer();
        
        customer.setId(3L);
        customer.setName("Bob Brown");
        customer.setEmail("bob.brown@example.com");
        customer.setPixKey("bob@pix");

        assertThat(customer.getId()).isEqualTo(3L);
        assertThat(customer.getName()).isEqualTo("Bob Brown");
        assertThat(customer.getEmail()).isEqualTo("bob.brown@example.com");
        assertThat(customer.getPixKey()).isEqualTo("bob@pix");
    }

    @Test
    @DisplayName("Should throw exception when setting null name")
    void shouldThrowExceptionWhenNameIsNull() {
        Customer customer = new Customer();
        
        assertThatThrownBy(() -> customer.setName(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Name cannot be null");
    }

    @Test
    @DisplayName("Should throw exception when setting null email")
    void shouldThrowExceptionWhenEmailIsNull() {
        Customer customer = new Customer();
        
        assertThatThrownBy(() -> customer.setEmail(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Email cannot be null");
    }

    @Test
    @DisplayName("Should test equals and hashCode")
    void shouldTestEqualsAndHashCode() {
        Customer customer1 = new Customer(1L, "Customer A", "a@example.com", "pixA");
        Customer customer2 = new Customer(1L, "Customer B", "b@example.com", "pixB"); // Same ID
        Customer customer3 = new Customer(2L, "Customer A", "a@example.com", "pixA"); // Different ID

        assertThat(customer1).isEqualTo(customer2);
        assertThat(customer1).hasSameHashCodeAs(customer2);
        
        assertThat(customer1).isNotEqualTo(customer3);
        assertThat(customer1).isNotEqualTo(null);
        assertThat(customer1).isNotEqualTo(new Object());
    }

    @Test
    @DisplayName("Should test toString")
    void shouldTestToString() {
        Customer customer = new Customer(1L, "Test Customer", "test@example.com", "testPix");
        
        String expectedString = """
            Customer{"id":1,"name":"Test Customer","email":"test@example.com","pixKey":"testPix"}
            """;
        
        assertThat(customer.toString()).isEqualToIgnoringNewLines(expectedString);
    }
    
}
