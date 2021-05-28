package com.example.payments_gateway1;

import com.example.payments_gateway1.repository.LoginRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = LoginRepository.class)
public class PaymentsGateway1Application {

    public static void main(String[] args) {
        SpringApplication.run(PaymentsGateway1Application.class, args);
    }

}
