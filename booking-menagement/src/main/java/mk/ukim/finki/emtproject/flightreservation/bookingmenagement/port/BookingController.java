package mk.ukim.finki.emtproject.flightreservation.bookingmenagement.port;


import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.application.BookingService;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.Booking;
import mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.model.BookingId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> findAll()
    {
        return bookingService.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> findById(@PathVariable("id") String bookingId)
    {
        return bookingService.findById(new BookingId(bookingId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



}
