package dst.ass1.jpa.model.impl;

import dst.ass1.jpa.model.ITrip;
import dst.ass1.jpa.model.ITripInfo;
import dst.ass1.jpa.model.ITripReceipt;
import dst.ass1.jpa.util.Constants;

import javax.persistence.*;
import java.util.Date;

/**
 * TripInfo entity implementation.
 */
@Entity
@Table(name = Constants.T_TRIP_INFO)
public class TripInfo implements ITripInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Constants.M_TRIP_INFO_COMPLETED)
    @Temporal(TemporalType.TIMESTAMP)
    private Date completed;

    @Column(name = Constants.M_TRIP_INFO_TOTAL, nullable = false)
    private Double total;

    @Column(name = Constants.M_TRIP_INFO_DISTANCE, nullable = false)
    private Double distance;

    @Column(name = Constants.M_TRIP_INFO_DRIVER_RATING)
    private Integer driverRating;

    @Column(name = Constants.M_TRIP_INFO_RIDER_RATING)
    private Integer riderRating;

    @OneToOne(mappedBy = Constants.M_TRIP_TRIP_INFO)
    private Trip trip;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = Constants.M_TRIP_INFO_RECEIPT_ID)
    private TripReceipt receipt;

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
    public void setCompleted(Date completed) {
        this.completed = completed;
    }

    @Override
    public Double getTotal() {
        return total;
    }

    @Override
    public void setTotal(Double total) {
        this.total = total;
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
        this.trip = (Trip) trip;
    }
    
    @Override
    public ITripReceipt getReceipt() {
        return receipt;
    }

    @Override
    public void setReceipt(ITripReceipt receipt) {
        this.receipt = (TripReceipt) receipt;
    }
}
