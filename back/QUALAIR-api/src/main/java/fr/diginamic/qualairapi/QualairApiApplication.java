package fr.diginamic.qualairapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QualairApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(QualairApiApplication.class, args);
    }

}
