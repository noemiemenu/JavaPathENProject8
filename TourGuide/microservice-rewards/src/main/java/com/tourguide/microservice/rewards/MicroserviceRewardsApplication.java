package com.tourguide.microservice.rewards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * The type Microservice rewards application.
 */
@SpringBootApplication(scanBasePackages = {"com.tourguide.library.beans", "com.tourguide.microservice.rewards"})
@EnableFeignClients(basePackages = {"com.tourguide.feign_clients"})
public class MicroserviceRewardsApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceRewardsApplication.class, args);
    }

}
