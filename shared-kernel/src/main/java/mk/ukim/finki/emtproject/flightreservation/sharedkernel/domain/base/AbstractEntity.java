package mk.ukim.finki.emtproject.flightreservation.sharedkernel.domain.base;

import lombok.Getter;
import org.springframework.data.util.ProxyUtils;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public abstract class AbstractEntity<ID extends DomainObjectId>
implements IdentifyableDomainObject<ID>{

    @EmbeddedId
    private ID id;

    public AbstractEntity() {

    }

    public AbstractEntity(ID id) {
        this.id=id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !getClass().equals(ProxyUtils.getUserClass(obj))) {
            return false;
        }

        var other = (AbstractEntity<?>) obj;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", getClass().getSimpleName(), id);
    }


}
