package dst.ass1.jpa.model.impl;

import dst.ass1.jpa.model.IPaymentInfo;
import dst.ass1.jpa.model.IRider;
import dst.ass1.jpa.model.ITrip;
import dst.ass1.jpa.util.Constants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = Constants.T_RIDER)
public class Rider implements IRider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Constants.M_RIDER_NAME)
    private String name;

    @Column(name = Constants.M_RIDER_TEL)
    private String tel;

    @Column(name = Constants.M_RIDER_AVG_RATING)
    private Double avgRating;

    @Column(name = Constants.M_RIDER_EMAIL, unique = true)
    private String email;

    @Column(name = Constants.M_RIDER_PASSWORD, length = 20) // SHA-1 produces 20 bytes
    private byte[] password;

    @OneToMany(mappedBy = "rider", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Trip.class)
    private Collection<ITrip> trips = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = PaymentInfo.class)
    @JoinTable(
        name = Constants.J_RIDER_PAYMENT_INFO,
        joinColumns = @JoinColumn(name = Constants.I_RIDER),
        inverseJoinColumns = @JoinColumn(name = Constants.I_PAYMENT_INFO)
    )
    private Collection<IPaymentInfo> paymentInfos = new ArrayList<>();

    public Rider() {
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTel() {
        return tel;
    }

    @Override
    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public Double getAvgRating() {
        return avgRating;
    }

    @Override
    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

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
        this.trips.add(trip);
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
