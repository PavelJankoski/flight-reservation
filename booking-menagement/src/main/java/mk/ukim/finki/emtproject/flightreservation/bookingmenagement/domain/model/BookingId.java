package mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model;

import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class BookingId extends DomainObjectId {

    public BookingId(String id) {
        super(id);
    }

    @SuppressWarnings("unused")
    protected BookingId(){
        super("");
    }
}
