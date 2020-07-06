package mk.ukim.finki.emtproject.flightreservation.sharedkernel.infra.eventlog;

import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.RemoteEventLog;

public interface RemoteEventLogService {
    String source();

    RemoteEventLog currentLog(long lastProcessedId);

}
