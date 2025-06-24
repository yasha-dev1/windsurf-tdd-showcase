package dst.ass1.jpa.model.impl;

import dst.ass1.jpa.model.IPaymentInfo;
import dst.ass1.jpa.model.IRider;
import dst.ass1.jpa.model.ITrip;
import dst.ass1.jpa.util.Constants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Rider entity implementation.
 * Extends PlatformUser with JOINED inheritance strategy.
 */
@Entity
@Table(name = Constants.T_RIDER, uniqueConstraints = {
    @UniqueConstraint(columnNames = {Constants.M_RIDER_EMAIL}),
    @UniqueConstraint(columnNames = {Constants.M_RIDER_EMAIL, Constants.M_RIDER_NAME})
})
@DiscriminatorValue("RIDER")
public class Rider extends PlatformUser implements IRider {

    @Column(name = Constants.M_RIDER_EMAIL, nullable = false, unique = true)
    private String email;

    @Column(name = Constants.M_RIDER_PASSWORD, nullable = false, length = 20)
    private byte[] password;

    @OneToMany(mappedBy = Constants.M_TRIP_RIDER, cascade = CascadeType.ALL)
    private Collection<ITrip> trips = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = Constants.J_RIDER_PAYMENT_INFO,
        joinColumns = @JoinColumn(name = Constants.I_RIDER),
        inverseJoinColumns = @JoinColumn(name = Constants.I_PAYMENT_INFO)
    )
    private Collection<IPaymentInfo> paymentInfos = new ArrayList<>();

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public byte[] getPassword() {
        return password;
    }

    @Override
    public void setPassword(byte[] password) {
        this.password = password;
    }

    @Override
    public Collection<ITrip> getTrips() {
        return trips;
    }

    @Override
    public void setTrips(Collection<ITrip> trips) {
        this.trips = trips;
    }

    @Override
    public void addTrip(ITrip trip) {
        if (trip != null) {
            this.trips.add(trip);
            trip.setRider(this);
        }
    }

    @Override
    public Collection<IPaymentInfo> getPaymentInfos() {
        return paymentInfos;
    }

    @Override
    public void setPaymentInfos(Collection<IPaymentInfo> paymentInfos) {
        this.paymentInfos = paymentInfos;
    }
}
