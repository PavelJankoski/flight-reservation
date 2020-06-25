package mk.ukim.finki.emtproject.flightreservation.customermenagement.domain.model;

import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.ValueObject;

import javax.persistence.*;

@Embeddable
public class CustomerInfo implements ValueObject {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name", nullable = false)),
            @AttributeOverride(name = "lastName", column = @Column(name = "last_name",nullable = false))
    })
    private FullName fullName;

    @Embedded
    private PassportInfo passportInfo;



}
