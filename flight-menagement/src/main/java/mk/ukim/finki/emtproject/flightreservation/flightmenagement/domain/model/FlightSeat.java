package mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model;

import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "flight_seat")
public class FlightSeat extends AbstractEntity<FlightSeatId> {
    @EmbeddedId
    private FlightSeatId id;

    @Version
    private Long version;

    @Column(nullable = false)
    private boolean isFree;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_class", nullable = false)
    private SeatClass seatClass;

    @Column(name = "seat_row", nullable = false)
    private int row;

    @Column(name = "seat_column", nullable = false)
    private int column;

    @Override
    public FlightSeatId id() {
        return id;
    }
}
