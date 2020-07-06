package mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.repository;

import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.Flight;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.FlightId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, FlightId> {

}
