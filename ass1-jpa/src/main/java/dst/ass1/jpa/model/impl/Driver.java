package dst.ass1.jpa.model.impl;

import dst.ass1.jpa.model.IDriver;
import dst.ass1.jpa.model.IEmployment;
import dst.ass1.jpa.model.IVehicle;
import dst.ass1.jpa.util.Constants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = Constants.T_DRIVER)
public class Driver implements IDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Constants.M_DRIVER_NAME)
    private String name;

    @Column(name = Constants.M_DRIVER_TEL)
    private String tel;

    @Column(name = Constants.M_DRIVER_AVG_RATING)
    private Double avgRating;

    @OneToMany(mappedBy = "id.driver", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Employment.class)
    private Collection<IEmployment> employments = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Vehicle.class)
    @JoinColumn(name = Constants.I_VEHICLE)
    private IVehicle vehicle;

    public Driver() {
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
    public Collection<IEmployment> getEmployments() {
        return employments;
    }

    @Override
    public void setEmployments(Collection<IEmployment> employments) {
        this.employments = employments;
    }

    @Override
    public void addEmployment(IEmployment employment) {
        this.employments.add(employment);
    }

    @Override
    public IVehicle getVehicle() {
        return vehicle;
    }

    @Override
    public void setVehicle(IVehicle vehicle) {
        this.vehicle = vehicle;
    }
}
