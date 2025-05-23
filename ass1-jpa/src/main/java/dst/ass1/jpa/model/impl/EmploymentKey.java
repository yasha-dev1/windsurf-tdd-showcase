package dst.ass1.jpa.model.impl;

import dst.ass1.jpa.model.IDriver;
import dst.ass1.jpa.model.IEmploymentKey;
import dst.ass1.jpa.model.IOrganization;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmploymentKey implements IEmploymentKey, Serializable {

    @ManyToOne(targetEntity = Driver.class)
    @JoinColumn(name = "driver_id")
    private IDriver driver;

    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "organization_id")
    private IOrganization organization;

    public EmploymentKey() {
    }

    public EmploymentKey(IDriver driver, IOrganization organization) {
        this.driver = driver;
        this.organization = organization;
    }

    @Override
    public IDriver getDriver() {
        return driver;
    }

    @Override
    public void setDriver(IDriver driver) {
        this.driver = driver;
    }

    @Override
    public IOrganization getOrganization() {
        return organization;
    }

    @Override
    public void setOrganization(IOrganization organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmploymentKey that = (EmploymentKey) o;
        return Objects.equals(driver, that.driver) && Objects.equals(organization, that.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driver, organization);
    }
}
