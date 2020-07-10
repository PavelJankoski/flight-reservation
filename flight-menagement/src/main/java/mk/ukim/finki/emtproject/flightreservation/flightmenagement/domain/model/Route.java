package mk.ukim.finki.emtproject.flightreservation.flightmenagement.domain.model;

import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.ValueObject;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Route implements ValueObject {
    @Column(name = "from")
    private final String from;
    @Column(name = "to")
    private final String to;

    public Route(@NonNull String from, @NonNull String to) {
        this.from = from;
        this.to = to;
    }

    @SuppressWarnings("unused")
    protected Route(){
        this.from = "";
        this.to = "";
    }

    public static Route newRoute(String from, String to){
        return new Route(from,to);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(from, route.from) &&
                Objects.equals(to, route.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "Route{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
