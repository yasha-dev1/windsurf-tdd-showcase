package dst.ass1.jpa.model.impl;

import dst.ass1.jpa.model.IDriver;
import dst.ass1.jpa.model.IEmploymentKey;
import dst.ass1.jpa.model.IOrganization;
import dst.ass1.jpa.util.Constants;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * Composite key for Employment entity, consisting of Driver and Organization references.
 */
@Embeddable
public class EmploymentKey implements IEmploymentKey, Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = Constants.I_DRIVER, nullable = false)
    private IDriver driver;

    @ManyToOne
    @JoinColumn(name = Constants.I_ORGANIZATION, nullable = false)
    private IOrganization organization;

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
        return Objects.equals(driver, that.driver) &&
                Objects.equals(organization, that.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driver, organization);
    }
}
