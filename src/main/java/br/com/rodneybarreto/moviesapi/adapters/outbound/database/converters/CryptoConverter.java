package br.com.rodneybarreto.moviesapi.adapters.outbound.database.converters;

import br.com.rodneybarreto.moviesapi.infrastructure.helpers.AESHelper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class CryptoConverter implements AttributeConverter<String, String> {

    private final AESHelper aesHelper;

    @Override
    public String convertToDatabaseColumn(String value) {
        if (value == null) return null;
        return aesHelper.encrypt(value);
    }

    @Override
    public String convertToEntityAttribute(String value) {
        if (value == null) return null;
        return aesHelper.decrypt(value);
    }

}
