package mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.events;

import lombok.Getter;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.*;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;
import java.util.Set;

@Getter
public class BookingCreated implements DomainEvent {

    private final BookingId bookingId;

    private final CustomerId customerId;

    private final Instant occurredOn;

    private final BookingStatus status;

    public BookingCreated(BookingId bookingId,CustomerId customerId,BookingStatus status, Instant occurredOn){
        this.bookingId = bookingId;
        this.customerId=customerId;
        this.status=status;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}
