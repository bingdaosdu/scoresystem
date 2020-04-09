package com.gde.integral.service;

import com.gde.integral.service.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * GdeIntegralApplication
 *
 * @author ~
 * @date 2019/6/25
 */
@SpringBootApplication
@EnableScheduling
public class GdeIntegralServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdeIntegralServiceApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }

}
