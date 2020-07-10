package mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "flights")
@Getter
public class Flight extends AbstractEntity<FlightId> {

//    @EmbeddedId
//    private FlightId id;

    @SuppressWarnings("unused")
    protected Flight(){}


    @Version
    private Long version;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "flight_status", nullable = false)
    private FlightStatus status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency"))
    })
    private Money price;

    @Column(nullable = false)
    private LocalDateTime dateAndTime;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "from", column = @Column(name = "departure_from")),
            @AttributeOverride(name = "to", column = @Column(name = "arrival_in"))
    })
    private Route route;
//
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "flight")
    private Set<FlightSeat> seats;

//
//    @Override
//    public FlightId id() {
//        return id;
//    }
        public Flight(LocalDateTime dateAndTime,Money price,Route route,FlightStatus status)
        {
                super(DomainObjectId.randomId(FlightId.class));
                this.dateAndTime=dateAndTime;
                this.price=price;
                this.route=route;
                this.status=status;

        }



}

