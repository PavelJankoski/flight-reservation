package mk.ukim.finki.emtproject.flightreservation.customermenagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base.ValueObject;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@Getter
public class PassportInfo implements ValueObject {
    @Column(name = "passport_number", nullable = false)
    private final String passportNumber;
    @Column(nullable = false)
    private final String nationality;
    @Column(name = "expiry_date",nullable = false)
    private final LocalDate expiryDate;

    public PassportInfo(@NonNull String passportNumber, @NonNull String nationality, @NonNull LocalDate expiryDate) {
        this.passportNumber=passportNumber;
        this.nationality=nationality;
        this.expiryDate=expiryDate;
    }

    @SuppressWarnings("unused")
    protected PassportInfo(){
        this.passportNumber="";
        this.nationality="";
        this.expiryDate = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassportInfo that = (PassportInfo) o;
        return Objects.equals(passportNumber, that.passportNumber) &&
                Objects.equals(nationality, that.nationality) &&
                Objects.equals(expiryDate, that.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportNumber, nationality, expiryDate);
    }

    @Override
    public String toString() {
        return "PassportInfo{" +
                "passportNumber='" + passportNumber + '\'' +
                ", nationality='" + nationality + '\'' +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
