package iit.cs445.models.products;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "analog_surveillance_camera")
public class AnalogSurveillanceCamera extends SecurityCamera<AnalogSurveillanceCamera> implements Repairable, Installable {

    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AnalogSurveillanceCamera{" +
                "description='" + description + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnalogSurveillanceCamera that = (AnalogSurveillanceCamera) o;

        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        return description != null ? description.hashCode() : 0;
    }

}
