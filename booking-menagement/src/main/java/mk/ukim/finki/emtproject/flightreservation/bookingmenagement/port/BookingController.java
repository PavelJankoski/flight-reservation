package mk.ukim.finki.emtproject.flightreservation.bookingmenagement.port;

import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.application.BookingService;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.*;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.port.dto.BookingDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public BookingId createBooking(@RequestBody BookingDTO bookingDTO) {
        Booking booking = new Booking(bookingDTO.getMoney(), new CustomerId(bookingDTO.getCustomerId()),BookingStatus.valueOf(bookingDTO.getStatus()));
        return bookingService.createBooking(booking, bookingDTO.getFlightId(), bookingDTO.getSeats());
    }

    @GetMapping("/delete")
    public Booking deleteBooking(@RequestBody BookingId bookingId)
    {
        return bookingService.deleteBooking(bookingId);
    }


}
