package dst.ass1.jpa.model.impl;

import dst.ass1.jpa.model.ITrip;
import dst.ass1.jpa.model.ITripInfo;
import dst.ass1.jpa.model.ITripReceipt;
import dst.ass1.jpa.util.Constants;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Constants.T_TRIP_INFO)
public class TripInfo implements ITripInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Constants.M_TRIP_INFO_COMPLETED)
    @Temporal(TemporalType.TIMESTAMP)
    private Date completed;

    @Column(name = Constants.M_TRIP_INFO_DISTANCE)
    private Double distance;

    @Column(name = Constants.M_TRIP_INFO_DRIVER_RATING)
    private Integer driverRating;

    @Column(name = Constants.M_TRIP_INFO_RIDER_RATING)
    private Integer riderRating;

    @OneToOne(mappedBy = "tripInfo", targetEntity = Trip.class)
    private ITrip trip;

    @OneToOne(mappedBy = "tripInfo", cascade = CascadeType.ALL, targetEntity = TripReceipt.class)
    private ITripReceipt receipt;

    public TripInfo() {
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
    public Date getCompleted() {
        return completed;
    }

    @Override
    public void setCompleted(Date date) {
        this.completed = date;
    }

    @Override
    public Double getDistance() {
        return distance;
    }

    @Override
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public Integer getDriverRating() {
        return driverRating;
    }

    @Override
    public void setDriverRating(Integer driverRating) {
        this.driverRating = driverRating;
    }

    @Override
    public Integer getRiderRating() {
        return riderRating;
    }

    @Override
    public void setRiderRating(Integer riderRating) {
        this.riderRating = riderRating;
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
    public ITripReceipt getReceipt() {
        return receipt;
    }

    @Override
    public void setReceipt(ITripReceipt receipt) {
        this.receipt = receipt;
    }
}
