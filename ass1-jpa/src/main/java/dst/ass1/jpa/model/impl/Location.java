package dst.ass1.jpa.model.impl;

import dst.ass1.jpa.model.ILocation;
import dst.ass1.jpa.util.Constants;

import javax.persistence.*;

@Entity
@Table(name = Constants.T_LOCATION)
public class Location implements ILocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Constants.M_LOCATION_LOCATION_ID)
    private Long locationId;

    @Column(name = Constants.M_LOCATION_NAME)
    private String name;

    public Location() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getLocationId() {
        return locationId;
    }

    @Override
    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
