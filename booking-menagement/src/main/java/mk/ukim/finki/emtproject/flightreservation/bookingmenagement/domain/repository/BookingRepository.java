package mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.repository;

import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.Booking;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.BookingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository <Booking, BookingId> {
}
