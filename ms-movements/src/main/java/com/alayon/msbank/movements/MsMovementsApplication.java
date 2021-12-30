package com.alayon.msbank.movements;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MsMovementsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsMovementsApplication.class, args);
    }
}
