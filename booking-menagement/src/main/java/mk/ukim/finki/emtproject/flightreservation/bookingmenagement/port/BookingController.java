package mk.ukim.finki.emtproject.flightreservation.bookingmenagement.port;

import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.application.BookingService;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.Booking;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.BookingId;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.CustomerId;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.port.dto.BookingDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public BookingId createBooking(@RequestBody BookingDTO bookingDTO) {
        Booking booking = new Booking(bookingDTO.getMoney(), new CustomerId(bookingDTO.getCustomerId()));
        return bookingService.createBooking(booking, bookingDTO.getFlightId(), bookingDTO.getSeats());
    }

    @GetMapping("/cancel")
    public Booking cancelBooking(@RequestBody BookingId bookingId)
    {
        return bookingService.cancelBooking(bookingId);
    }


}
