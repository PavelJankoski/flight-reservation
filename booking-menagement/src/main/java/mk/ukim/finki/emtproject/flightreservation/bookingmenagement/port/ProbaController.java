package mk.ukim.finki.emtproject.flightreservation.bookingmenagement.port;

import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.application.BookingService;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.Booking;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.BookingFlightSeat;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.BookingId;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.CustomerId;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.port.dto.BookingDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/bookings")
public class ProbaController {

    private final BookingService bookingService;


    public ProbaController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/test")
    public BookingId createBooking(@RequestBody BookingDTO bookingDTO) {
        Booking booking = new Booking(bookingDTO.getMoney(), new CustomerId(bookingDTO.getCustomerId()));
        return bookingService.createBooking(booking, bookingDTO.getFlightId(), bookingDTO.getSeats());
    }


}
