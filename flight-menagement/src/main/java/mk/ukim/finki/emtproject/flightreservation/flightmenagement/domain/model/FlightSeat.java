package mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "flight_seats")
@Getter
public class FlightSeat extends AbstractEntity<FlightSeatId> {
//
//    @EmbeddedId
//    private FlightSeatId id;
    @SuppressWarnings("unused")
    protected FlightSeat(){}


    @Embedded
    @AttributeOverride(name = "id",column = @Column(name = "book_id",nullable = false))
    private BookingId bookingId;

    @Version
    private Long version;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FlightSeatStatus flightSeatStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_class", nullable = false)
    private SeatClass seatClass;

    @Column(name = "seat_row", nullable = false)
    private int row;

    @Column(name = "seat_column", nullable = false)
    private int column;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

//    @Override
//    public FlightSeatId id() {
//        return id;
//    }

    public void  changeSeatStatus(@NonNull FlightSeatStatus flightSeatStatus)
    {
            this.flightSeatStatus=flightSeatStatus;
    }

    public void setBookingId(BookingId bookingId)
    {
        this.bookingId=bookingId;
    }

    public FlightSeat(BookingId bookingId,FlightSeatStatus flightSeatStatus,int column,int row,SeatClass seatClass,Flight flight)
    {
            super(DomainObjectId.randomId(FlightSeatId.class));
            this.bookingId=bookingId;
            this.flightSeatStatus=flightSeatStatus;
            this.column=column;
            this.row=row;
            this.seatClass=seatClass;
            this.flight=flight;
    }

}
