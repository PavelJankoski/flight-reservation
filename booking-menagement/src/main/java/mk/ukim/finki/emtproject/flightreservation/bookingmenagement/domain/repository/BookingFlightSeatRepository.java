package mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.repository;

import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.BookingFlightSeat;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.FlightSeatId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingFlightSeatRepository extends JpaRepository<BookingFlightSeat, FlightSeatId> {
}
