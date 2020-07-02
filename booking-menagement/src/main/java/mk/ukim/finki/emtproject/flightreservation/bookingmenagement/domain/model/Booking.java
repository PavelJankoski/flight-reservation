package mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@NoArgsConstructor
@Getter
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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Money totalPrice;


    //@Embedded
    //@AttributeOverride(name="customerId",column = @Column(name="customer_id",nullable = false))
    //private CustomerId customerId;

    //@Embedded
    //@AttributeOverride(name="flightId",column = @Column(name="flight_id",nullable = false))
    //private FlightId flightId;


    public Booking(@NotNull Money totalPrice
            //, @NotNull CustomerId customerId, @NotNull FlightId flightId
    ) {
        super(DomainObjectId.randomId(BookingId.class));
        this.dateAndTime = LocalDateTime.now();
        this.status = BookingStatus.RESERVED;
        this.totalPrice = totalPrice;
        //this.customerId = customerId;
        //this.flightId = flightId;

    }

    public void changeBookingStatus(BookingStatus bookingStatus){
        this.status = bookingStatus;
    }

    @Override
    public BookingId id() {
        return id;
    }
}
