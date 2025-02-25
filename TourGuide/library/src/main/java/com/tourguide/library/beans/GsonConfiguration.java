package com.tourguide.library.beans;

import com.tourguide.library.type_adapters.MoneyTypeAdapterFactory;
import com.tourguide.library.type_adapters.UnixEpochDateTypeAdapter;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * The type Gson configuration.
 */
@Configuration
public class GsonConfiguration {

    /**
     * Type adapter registration gson builder customizer.
     *
     * @return the gson builder customizer
     */
    @Bean
    public GsonBuilderCustomizer typeAdapterRegistration() {
        return builder -> {
            builder.registerTypeAdapter(Date.class, new UnixEpochDateTypeAdapter());
            builder.registerTypeAdapterFactory(new MoneyTypeAdapterFactory());
        };
    }
}