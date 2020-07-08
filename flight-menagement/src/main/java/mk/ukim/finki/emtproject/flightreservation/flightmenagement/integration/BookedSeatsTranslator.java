package mk.ukim.finki.emtproject.flightreservation.flightmenagement.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.DomainEvent;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.infra.eventlog.RemoteEventTranslator;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.infra.eventlog.StoredDomainEvent;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BookedSeatsTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    @Override
    public boolean supports(StoredDomainEvent storedDomainEvent) {
        return storedDomainEvent.domainEventClassName().equals("mk.ukim.finki.emtproject.flightreservation.bookingmenagement.domain.events.BookedSeats");
    }

    @Override
    public Optional<DomainEvent> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(objectMapper, BookedSeatsEvent.class));
    }
}
