package br.com.rodneybarreto.moviesapi.adapter.out.persistence.converter;

import br.com.rodneybarreto.moviesapi.infrastructure.helper.AESHelper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CryptoConverter implements AttributeConverter<String, String> {

    private final AESHelper aesHelper;

    public CryptoConverter(AESHelper aesHelper) {
        this.aesHelper = aesHelper;
    }

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
