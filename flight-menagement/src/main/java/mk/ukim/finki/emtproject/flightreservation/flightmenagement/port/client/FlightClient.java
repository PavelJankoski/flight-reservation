package mk.ukim.finki.emtproject.flightreservation.flightmenagement.port.client;


import mk.ukim.finki.emtproject.flightreservation.flightmenagement.application.BookingCatalog;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.Booking;
import mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model.BookingId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class FlightClient implements BookingCatalog {


    private static final Logger LOGGER = LoggerFactory.getLogger(FlightClient.class);

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public FlightClient(@Value("http://localhost/8082") String serverUrl,
                        @Value("${app.booking-catalog.connect-timeout-ms}") int connectTimeout,
                        @Value("${app.booking-catalog.read-timeout-ms}") int readTimeout) {
        this.serverUrl = serverUrl;
        restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        // Never ever do a remote call without a finite timeout!
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(serverUrl);
    }



    @Override
    public List<Booking> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/booking").build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Booking>>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving products", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public Booking findById(BookingId bookingId) {
        try {
            return restTemplate.exchange(uri().path("/api/products/"+bookingId.getId()).build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<Booking>() {
                    }).getBody();
        } catch (Exception ex) {
            LOGGER.error("Error retrieving product by id", ex);
            return null;
        }
    }
}
