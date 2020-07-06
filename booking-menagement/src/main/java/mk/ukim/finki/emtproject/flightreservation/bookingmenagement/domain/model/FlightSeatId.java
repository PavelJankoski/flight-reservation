package mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model;

import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class FlightSeatId extends DomainObjectId {

    public FlightSeatId(String id) {
        super(id);
    }


    @SuppressWarnings("unused")
    protected FlightSeatId(){
        super("");
    }
}
