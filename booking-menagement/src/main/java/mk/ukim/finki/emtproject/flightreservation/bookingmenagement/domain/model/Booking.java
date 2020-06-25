package mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model;

import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking extends AbstractEntity<BookingId> {

    @EmbeddedId
    private BookingId id;

    @Version
    private Long version;

    @Column(nullable = false)
    private LocalDateTime dateAndTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", nullable = false)
    private BookingStatus status;


    @Override
    public BookingId id() {
        return id;
    }
}
