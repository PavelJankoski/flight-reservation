package mk.ukim.finki.emtproject.flightreservation.sharedkernel.infra.eventlog;

import com.fasterxml.jackson.databind.ObjectMapper;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.DomainEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DomainEventLogService {

    private final ObjectMapper objectMapper;
    private final StoredDomainEventRepository storedDomainEventRepository;

    public DomainEventLogService(ObjectMapper objectMapper, StoredDomainEventRepository storedDomainEventRepository) {
        this.objectMapper = objectMapper;
        this.storedDomainEventRepository = storedDomainEventRepository;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void append(DomainEvent domainEvent){
        StoredDomainEvent storedEvent = new StoredDomainEvent(domainEvent, objectMapper);
        this.storedDomainEventRepository.saveAndFlush(storedEvent);
    }

    public List<StoredDomainEvent> retrieveLog(long lastProcessedEventId){
        long max = storedDomainEventRepository.findHighestDomainEventId();
        return  storedDomainEventRepository.findEventsBetween(lastProcessedEventId, max);
    }

}
