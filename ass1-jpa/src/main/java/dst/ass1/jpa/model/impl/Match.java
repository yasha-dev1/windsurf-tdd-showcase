package dst.ass1.jpa.model.impl;

import dst.ass1.jpa.model.IDriver;
import dst.ass1.jpa.model.IMatch;
import dst.ass1.jpa.model.IMoney;
import dst.ass1.jpa.model.ITrip;
import dst.ass1.jpa.model.IVehicle;
import dst.ass1.jpa.util.Constants;

import javax.persistence.*;
import java.util.Date;

/**
 * Match entity implementation with embedded Money value type.
 */
@Entity
@Table(name = Constants.T_MATCH)
public class Match implements IMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.M_MATCH_DATE, nullable = false)
    private Date date;

    @Embedded
    private IMoney fare;

    @OneToOne(mappedBy = Constants.M_TRIP_MATCH)
    private ITrip trip;

    @ManyToOne
    @JoinColumn(name = Constants.I_VEHICLE)
    private IVehicle vehicle;

    @ManyToOne
    @JoinColumn(name = Constants.I_DRIVER)
    private IDriver driver;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public IMoney getFare() {
        return fare;
    }

    @Override
    public void setFare(IMoney fare) {
        this.fare = fare;
    }

    @Override
    public ITrip getTrip() {
        return trip;
    }

    @Override
    public void setTrip(ITrip trip) {
        this.trip = trip;
    }

    @Override
    public IVehicle getVehicle() {
        return vehicle;
    }

    @Override
    public void setVehicle(IVehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public IDriver getDriver() {
        return driver;
    }

    @Override
    public void setDriver(IDriver driver) {
        this.driver = driver;
    }
}
