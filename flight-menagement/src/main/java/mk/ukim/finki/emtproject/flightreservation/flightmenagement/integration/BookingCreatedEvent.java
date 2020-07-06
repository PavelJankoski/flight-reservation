package mk.ukim.finki.emtproject.flightreservation.flightmenagement.integration;


import lombok.Getter;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.BookingId;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.BookingStatus;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.CustomerId;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;
import java.util.Set;

@Getter
public class BookingCreatedEvent implements DomainEvent {

    private final BookingId bookingId;

    private final CustomerId customerId;

    private final BookingStatus status;

    private final Instant occurredOn;



    public BookingCreatedEvent(BookingId bookingId, CustomerId customerId, BookingStatus status, Instant occurredOn){
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
