package mk.ukim.finki.emtproject.flightreservation.flightmenagement;


import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.*;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.repository.FlightRepository;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.repository.FlightSeatRepository;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.financial.Money;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;

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
    FlightId flight1 = DomainObjectId.randomId(FlightId.class);
    FlightId flight2 = DomainObjectId.randomId(FlightId.class);
    if(flightRepository.findAll().size()==0)
    {
        var flights = new ArrayList< Flight >();

        flights.add(createFlight(flight1,LocalDateTime.of(2020, Month.JULY,7,10,10,30),new Money(Currency.MKD,2000),new Route("Skopje","Madrid"),FlightStatus.SCHEDULED));
        flights.add(createFlight(flight2,LocalDateTime.of(2020, Month.JULY,9,10,40,30),new Money(Currency.MKD,2000),new Route("Skopje","Amsterdam"),FlightStatus.SCHEDULED));
        flightRepository.saveAll(flights);
    }
    if(flightSeatRepository.findAll().size()==0)
    {
        var seats=new ArrayList<FlightSeat>();
        FlightSeat fs=createFlightSeat(new FlightSeatId("seat1"),new BookingId("nema"),FlightSeatStatus.AVAILABLE,3,4,SeatClass.ECONOMY, flight1);
        seats.add(fs);
        seats.add(createFlightSeat(new FlightSeatId("seat2"),new BookingId("nema"),FlightSeatStatus.AVAILABLE,4,5,SeatClass.ECONOMY,flight1));

        seats.add(createFlightSeat(new FlightSeatId("seat3"),new BookingId("nema"),FlightSeatStatus.AVAILABLE,6,4,SeatClass.ECONOMY,flight2));


        flightSeatRepository.saveAll(seats);
    }


   }

    private Flight createFlight(FlightId flightId, LocalDateTime localDateTime, Money money, Route route, FlightStatus flightStatus)
    {
            var flight=new Flight(flightId,localDateTime,money,route,flightStatus);
            return flight;
    }

    private FlightSeat createFlightSeat(FlightSeatId flightSeatId,BookingId bookingId,FlightSeatStatus flightSeatStatus,int column,int row,SeatClass seatClass,FlightId flightId)
    {
        Flight flight=flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException());
        var flightSeat = new FlightSeat(flightSeatId,bookingId,flightSeatStatus,column,row,seatClass,flight);
        return flightSeat;

    }


}
