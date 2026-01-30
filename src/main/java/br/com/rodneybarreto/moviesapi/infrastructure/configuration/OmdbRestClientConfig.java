package br.com.rodneybarreto.moviesapi.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class OmdbRestClientConfig {

    @Value("${omdb.api.base-url}")
    private String baseUrl;

    @Value("${omdb.api.key}")
    private String apiKey;

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder.baseUrl(baseUrl + "/?apikey=" + apiKey).build();
    }

}
