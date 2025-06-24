package dst.ass1.jpa.model.impl;

import dst.ass1.jpa.model.IDriver;
import dst.ass1.jpa.model.IEmployment;
import dst.ass1.jpa.model.IVehicle;
import dst.ass1.jpa.util.Constants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Driver entity implementation.
 * Extends PlatformUser with JOINED inheritance strategy.
 */
@Entity
@Table(name = Constants.T_DRIVER)
@DiscriminatorValue("DRIVER")
public class Driver extends PlatformUser implements IDriver {

    @OneToMany(mappedBy = "id.driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<IEmployment> employments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = Constants.I_VEHICLE)
    private IVehicle vehicle;

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
        if (employment != null) {
            this.employments.add(employment);
            // Set this driver in the employment key if not already set
            if (employment.getId() != null && employment.getId().getDriver() == null) {
                employment.getId().setDriver(this);
            }
        }
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
