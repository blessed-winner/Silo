package org.xenon.silo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SiloApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiloApplication.class, args);
    }

}
