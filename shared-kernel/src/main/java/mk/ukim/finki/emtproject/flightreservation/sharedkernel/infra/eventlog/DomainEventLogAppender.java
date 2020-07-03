package mk.ukim.finki.emtproject.flightreservation.sharedkernel.infra.eventlog;

import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.DomainEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class DomainEventLogAppender {
    private final DomainEventLogService domainEventLogService;

    public DomainEventLogAppender(DomainEventLogService domainEventLogService) {
        this.domainEventLogService = domainEventLogService;
    }

    @TransactionalEventListener
    public void onDomainEvent(DomainEvent domainEvent){
        domainEventLogService.append(domainEvent);
    }
}
