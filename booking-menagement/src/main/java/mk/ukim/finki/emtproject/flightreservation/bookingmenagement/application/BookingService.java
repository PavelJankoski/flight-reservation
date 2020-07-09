package mk.ukim.finki.emtproject.flightreservation.bookingmenagement.application;

import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.events.BookedSeats;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.events.BookingCreated;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.*;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.repository.BookingFlightSeatRepository;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.repository.BookingRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingService  {


    private final ApplicationEventPublisher applicationEventPublisher;

    private final Validator validator;

    private final BookingRepository bookingRepository;

    private final BookingFlightSeatRepository bookingFlightSeatRepository;


    public BookingService(ApplicationEventPublisher applicationEventPublisher, Validator validator, BookingRepository bookingRepository, BookingFlightSeatRepository bookingFlightSeatRepository) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.validator = validator;
        this.bookingRepository = bookingRepository;
        this.bookingFlightSeatRepository = bookingFlightSeatRepository;
    }


    @Transactional
    public BookingId createBooking(@NonNull Booking booking, String flightId, Set<String> seats) {
        Objects.requireNonNull(booking,"Booking must not be null");
        var constraintViolations = validator.validate(booking);

        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The form is not valid", constraintViolations);
        }

        var newBooking = bookingRepository.saveAndFlush(booking);
        Set<BookingFlightSeat> flightSeats = seats.stream().map(s -> new BookingFlightSeat(new FlightSeatId(s), new FlightId(flightId), newBooking.id()))
                .collect(Collectors.toSet());
        List<BookingFlightSeat> bookingFlightSeats = bookingFlightSeatRepository.saveAll(flightSeats);
        applicationEventPublisher.publishEvent(new BookingCreated(newBooking.id(),newBooking.getCustomerId(),newBooking.getStatus(),newBooking.getBookedOn()));
        bookingFlightSeats.forEach(bookedSeat ->
                applicationEventPublisher.publishEvent(new BookedSeats(bookedSeat.getBookingId(),Instant.now(),bookedSeat.getFlightId(),bookedSeat.getId(),newBooking.getStatus())));
        return newBooking.id();
    }


    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> findById(BookingId bookingId) {
        return bookingRepository.findById(bookingId);
    }

    @Transactional
    public Booking deleteBooking(BookingId bookingId) {
        Booking booking=findById(bookingId).orElseThrow(() -> new RuntimeException("error"));
            booking.changeBookingStatus(BookingStatus.valueOf("CANCELLED"));
            bookingRepository.saveAndFlush(booking);
            applicationEventPublisher.publishEvent(new BookingCreated(booking.id(),booking.getCustomerId(),booking.getStatus(),booking.getBookedOn()));
        return  booking;
    }
}
