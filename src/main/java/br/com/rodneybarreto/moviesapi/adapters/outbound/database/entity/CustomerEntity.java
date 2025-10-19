package br.com.rodneybarreto.moviesapi.adapters.outbound.database.entity;

import br.com.rodneybarreto.moviesapi.adapters.outbound.database.converters.CryptoConverter;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class CustomerEntity {

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

}
