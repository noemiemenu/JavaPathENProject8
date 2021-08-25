package com.tourguide.microservice.tourguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = {"com.tourguide.library.beans", "com.tourguide.microservice.tourguide"})
@EnableFeignClients(basePackages = {"com.tourguide.feign_clients"})
public class MicroserviceTourguideApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceTourguideApplication.class, args);
    }

}
