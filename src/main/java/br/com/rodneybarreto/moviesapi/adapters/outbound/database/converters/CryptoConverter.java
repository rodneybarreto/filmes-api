package br.com.rodneybarreto.moviesapi.adapters.outbound.database.converters;

import br.com.rodneybarreto.moviesapi.infrastructure.helpers.AESHelper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CryptoConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String value) {
        if (value == null) return null;
        return AESHelper.encrypt(value);
    }

    @Override
    public String convertToEntityAttribute(String value) {
        if (value == null) return null;
        return AESHelper.decrypt(value);
    }

}
