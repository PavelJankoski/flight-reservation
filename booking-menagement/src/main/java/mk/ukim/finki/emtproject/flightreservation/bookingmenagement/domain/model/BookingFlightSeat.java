package mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "booking_seats")
@NoArgsConstructor
@Getter
public class BookingFlightSeat extends AbstractEntity<FlightSeatId> {


    @Embedded
    @AttributeOverride(name = "id",column = @Column(name = "booking_id",nullable = false))
    private BookingId bookingId;


    @Embedded
    @AttributeOverride(name = "id",column = @Column(name = "flight_id",nullable = false))
    private  FlightId flightId;



    public BookingFlightSeat(FlightSeatId flightSeatId, FlightId flightId,BookingId bookingId )
    {
        super(flightSeatId);
        this.flightId=flightId;
        this.bookingId=bookingId;
    }

}
