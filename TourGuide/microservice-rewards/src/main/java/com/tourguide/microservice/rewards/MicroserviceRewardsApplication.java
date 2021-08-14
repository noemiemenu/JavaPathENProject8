package com.tourguide.microservice.rewards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.tourguide.feign_clients"})
public class MicroserviceRewardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceRewardsApplication.class, args);
    }

}
