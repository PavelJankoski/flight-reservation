package mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bookings")
@NoArgsConstructor
@Getter
public class Booking extends AbstractEntity<BookingId> {

    @Version
    private Long version;

    @Column(nullable = false)
    private Instant bookedOn;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", nullable = false)
    private BookingStatus status;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "customer_id", nullable = false))
    private CustomerId customerId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_id")
    private Set<BookingFlightSeat> bookedSeats;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Money totalPrice;


    public Booking(Money totalPrice,CustomerId customerId) {
        super(DomainObjectId.randomId(BookingId.class));
        this.bookedOn = Instant.now();
        this.bookedSeats=new HashSet<>();
        this.status = BookingStatus.RESERVED;
        this.totalPrice = totalPrice;
        this.customerId=customerId;
    }

    public void setBookedSeats(Set<BookingFlightSeat> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public void changeBookingStatus(BookingStatus bookingStatus) {
        this.status = bookingStatus;
    }

}
