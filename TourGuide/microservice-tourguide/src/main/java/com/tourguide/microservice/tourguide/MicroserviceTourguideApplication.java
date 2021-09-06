package com.tourguide.microservice.tourguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * The type Microservice tourguide application.
 */
@SpringBootApplication(scanBasePackages = {"com.tourguide.library.beans", "com.tourguide.microservice.tourguide"})
@EnableFeignClients(basePackages = {"com.tourguide.feign_clients"})
public class MicroserviceTourguideApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceTourguideApplication.class, args);
    }

}
