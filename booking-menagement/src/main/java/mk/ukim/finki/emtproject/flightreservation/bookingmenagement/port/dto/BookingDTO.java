package mk.ukim.finki.emtproject.flightreservation.bookingmenagement.port.dto;

import lombok.Data;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.FlightSeatId;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.financial.Money;

import java.io.Serializable;
import java.util.Set;

@Data
public class BookingDTO implements Serializable {

    private Money money;

    private String customerId;

    private String flightId;

    private Set<String>seats;

}
