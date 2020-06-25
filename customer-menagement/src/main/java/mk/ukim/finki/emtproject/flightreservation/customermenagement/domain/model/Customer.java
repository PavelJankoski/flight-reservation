package mk.ukim.finki.emtproject.flightreservation.customermenagement.domain.model;

import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer extends AbstractEntity<CustomerId> {

    @EmbeddedId
    private CustomerId id;

    @Version
    private Long version;

    @Column(nullable = false)
    private String email;

    @Embedded
    private CustomerInfo info;

    @Override
    public CustomerId id() {
        return id;
    }
}
