package mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.repository;

import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.BookingId;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.FlightSeat;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.FlightSeatId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightSeatRepository extends JpaRepository<FlightSeat, FlightSeatId> {

    List<FlightSeat> findAllByBookingId(BookingId bookingId);
}
