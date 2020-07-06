package mk.ukim.finki.emtproject.flightreservation.flightmenagement.application;

import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.Booking;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.BookingId;

import java.util.List;

public interface BookingCatalog {

    List<Booking> findAll();

    Booking findById(BookingId bookingId);


}
