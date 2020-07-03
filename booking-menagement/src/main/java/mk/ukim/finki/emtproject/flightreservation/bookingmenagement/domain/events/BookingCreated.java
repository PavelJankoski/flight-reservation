package mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.events;

import lombok.Getter;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.BookingId;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

@Getter
public class BookingCreated implements DomainEvent {

    private final BookingId bookingId;

    private final Instant occurredOn;

    public BookingCreated(BookingId bookingId, Instant occurredOn){
        this.bookingId = bookingId;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}
