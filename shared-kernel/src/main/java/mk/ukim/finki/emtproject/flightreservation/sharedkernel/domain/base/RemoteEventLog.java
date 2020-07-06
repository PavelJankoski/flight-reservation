package mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base;

import mk.ukim.finki.emtproject.flightreservation.sharedkernel.infra.eventlog.StoredDomainEvent;

import java.util.List;

public interface RemoteEventLog {

    List<StoredDomainEvent> events();
}
