package mk.ukim.finki.emtproject.flightreservation.flightmenagement.port;


import mk.ukim.finki.emtproject.flightreservation.flightmenagement.application.FlightCatalog;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.Flight;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.FlightId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

 private final FlightCatalog flightCatalog;


   FlightController(FlightCatalog flightCatalog)
   {
     this.flightCatalog=flightCatalog;
   }

   @GetMapping("/{id}")
  public ResponseEntity<Flight>findById(@PathVariable("id") String flightId)
   {

    return flightCatalog.findById(new FlightId(flightId))
              .map(ResponseEntity::ok)
              .orElse(ResponseEntity.notFound().build());

   }

   public List<Flight> findAll()
   {
     return flightCatalog.findAll();
   }



}
