package mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.AbstractEntity;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "flight_seats")
@Getter
public class FlightSeat extends AbstractEntity<FlightSeatId> {

    @EmbeddedId
    private FlightSeatId id;

    @Embedded
    @AttributeOverride(name = "id",column = @Column(name = "book_id",nullable = false))
    private BookingId bookingId;

    @Version
    private Long version;

    @Column(nullable = false)
    private FlightSeatStatus flightSeatStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_class", nullable = false)
    private SeatClass seatClass;

    @Column(name = "seat_row", nullable = false)
    private int row;

    @Column(name = "seat_column", nullable = false)
    private int column;

    @ManyToOne
    private Flight flight;

    @Override
    public FlightSeatId id() {
        return id;
    }

    public void  setFlightStatus(@NonNull FlightSeatStatus flightSeatStatus)
    {
            this.flightSeatStatus=flightSeatStatus;
    }

    public void setBookingId(BookingId bookingId)
    {
        this.bookingId=bookingId;
    }



}
