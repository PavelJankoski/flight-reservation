package mk.ukim.finki.emtproject.flightreservation.flightmenagement.port.client;


import com.fasterxml.jackson.core.type.TypeReference;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.RemoteEventLog;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.infra.eventlog.RemoteEventLogService;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.infra.eventlog.StoredDomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class FlightClient implements RemoteEventLogService {


    private static final Logger LOGGER = LoggerFactory.getLogger(FlightClient.class);

    private final RestTemplate restTemplate;

    private final String serverUrl;

    public FlightClient() {
        this.serverUrl = "http://localhost:8082";
        restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        // Never ever do a remote call without a finite timeout!
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(10000);
        restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(serverUrl);
    }

    @Override
    public String source() {
        return this.serverUrl;
    }

    @Override
    public RemoteEventLog currentLog(long lastProcessedId) {
        ResponseEntity<StoredDomainEvent[]> response = restTemplate.getForEntity(getUrl(lastProcessedId), StoredDomainEvent[].class);
        return () -> Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    private String getUrl(long lastId){
        return serverUrl + "/api/event-log/" + lastId;
    }
}

