package mk.ukim.finki.emtproject.flightreservation.flightmenagement;

import mk.ukim.finki.emtproject.flightreservation.sharedkernel.SharedConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@Import(SharedConfiguration.class)

public class FlightMenagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightMenagementApplication.class, args);
    }

}
