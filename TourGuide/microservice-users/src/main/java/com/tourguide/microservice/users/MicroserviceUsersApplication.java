package com.tourguide.microservice.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Microservice users application.
 */
@SpringBootApplication(scanBasePackages = {"com.tourguide.library.beans", "com.tourguide.microservice.users"})
public class MicroserviceUsersApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceUsersApplication.class, args);
    }

}
