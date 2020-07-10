package mk.ukim.finki.emtproject.flightreservation.flightmenagement;


import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.*;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.repository.FlightRepository;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.repository.FlightSeatRepository;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.financial.Money;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataGenerator {


    private final FlightRepository flightRepository;

    private final FlightSeatRepository flightSeatRepository;

    public DataGenerator(FlightRepository flightRepository, FlightSeatRepository flightSeatRepository) {
        this.flightRepository = flightRepository;
        this.flightSeatRepository = flightSeatRepository;
    }

    @PostConstruct
    @Transactional
    public void generateData() {
    Flight flight1 = createFlight(LocalDateTime.of(2020, Month.JULY,7,10,10,30),new Money(Currency.MKD,2000),new Route("Skopje","Madrid"),FlightStatus.SCHEDULED);
    Flight flight2 = createFlight(LocalDateTime.of(2020, Month.JULY,9,10,40,30),new Money(Currency.MKD,2000),new Route("Skopje","Amsterdam"),FlightStatus.SCHEDULED);
        if(flightRepository.findAll().size()==0)
    {
        List<Flight> flights = new ArrayList<>();

        flights.add(flight1);
        flights.add(flight2);
        flightRepository.saveAll(flights);
    }
    if(flightSeatRepository.findAll().size()==0)
    {
        var seats=new ArrayList<FlightSeat>();
        FlightSeat fs=createFlightSeat(new BookingId("nema"),FlightSeatStatus.AVAILABLE,3,4,SeatClass.ECONOMY, flight1.getId());
        seats.add(fs);
        seats.add(createFlightSeat(new BookingId("nema"),FlightSeatStatus.AVAILABLE,4,5,SeatClass.ECONOMY,flight1.getId()));

        seats.add(createFlightSeat(new BookingId("nema"),FlightSeatStatus.AVAILABLE,6,4,SeatClass.ECONOMY,flight2.getId()));


        flightSeatRepository.saveAll(seats);
    }


   }

    private Flight createFlight(LocalDateTime localDateTime, Money money, Route route, FlightStatus flightStatus)
    {
            var flight=new Flight(localDateTime,money,route,flightStatus);
            return flight;
    }

    private FlightSeat createFlightSeat(BookingId bookingId,FlightSeatStatus flightSeatStatus,int column,int row,SeatClass seatClass,FlightId flightId)
    {
        Flight flight=flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException());
        var flightSeat = new FlightSeat(bookingId,flightSeatStatus,column,row,seatClass,flight);
        return flightSeat;

    }


}
