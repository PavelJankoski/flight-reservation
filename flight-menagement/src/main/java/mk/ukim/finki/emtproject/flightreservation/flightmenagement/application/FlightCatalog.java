package mk.ukim.finki.emtproject.flightreservation.flightmenagement.application;


import lombok.AllArgsConstructor;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.*;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.repository.FlightRepository;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.repository.FlightSeatRepository;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.integration.BookedSeatsEvent;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.integration.BookingCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class FlightCatalog {

    private final FlightRepository flightRepository;
    private final FlightSeatRepository flightSeatRepository;


    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onBookingCreatedEvent(BookingCreatedEvent event) {
        if (event.getStatus() == BookingStatus.CANCELLED) {
            List<FlightSeat> seats = flightSeatRepository.findAllByBookingId(event.getBookingId());
            for (FlightSeat seat : seats) {
                seat.changeSeatStatus(FlightSeatStatus.AVAILABLE);
                seat.setBookingId(new BookingId("empty"));
                flightSeatRepository.save(seat);
            }
        }

    }


    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onBookedSeatsEvent(BookedSeatsEvent event) {
        if (event.getBookingStatus() == BookingStatus.RESERVED) {
            FlightSeat flightSeat = flightSeatRepository.findById(event.getFlightSeatId()).orElseThrow(RuntimeException::new);
            if (flightSeat.getFlightSeatStatus() == FlightSeatStatus.RESERVED) {
                throw new RuntimeException("The seat is already taken");
            }
            flightSeat.changeSeatStatus(FlightSeatStatus.RESERVED);
            flightSeat.setBookingId(event.getBookingId());
            flightSeatRepository.save(flightSeat);
        }
    }


    public Optional<Flight> findById(FlightId flightId) {
        Objects.requireNonNull(flightId, "FlightId must not be null");
        return flightRepository.findById(flightId);

    }


    public List<Flight> findAll() {
        return flightRepository.findAll();
    }


}
