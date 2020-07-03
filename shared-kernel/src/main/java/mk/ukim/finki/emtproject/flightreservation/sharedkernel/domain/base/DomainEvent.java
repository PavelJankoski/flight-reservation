package mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base;

import java.time.Instant;

public interface DomainEvent{
    Instant occurredOn();

}
