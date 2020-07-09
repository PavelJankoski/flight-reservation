package mk.ukim.finki.emtproject.flightreservation.flightmenagement.integration;

import lombok.Getter;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.BookingId;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.BookingStatus;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.FlightId;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.FlightSeatId;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

@Getter
public class BookedSeatsEvent implements DomainEvent {


    private final BookingId bookingId;
    private final Instant occurredOn;
    private final FlightId flightId;
    private final FlightSeatId flightSeatId;
    private final BookingStatus bookingStatus;

    public BookedSeatsEvent(BookingId bookingId, Instant occurredOn, FlightId flightId, FlightSeatId flightSeatId, BookingStatus bookingStatus) {
        this.bookingId = bookingId;
        this.occurredOn = occurredOn;
        this.flightId = flightId;
        this.flightSeatId = flightSeatId;
        this.bookingStatus = bookingStatus;
    }


    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}
