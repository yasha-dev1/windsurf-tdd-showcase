package dst.ass1.jpa.model.impl;

import dst.ass1.jpa.model.IEmployment;
import dst.ass1.jpa.model.IEmploymentKey;
import dst.ass1.jpa.util.Constants;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Employment entity implementation with composite key.
 */
@Entity
@Table(name = Constants.T_EMPLOYMENT)
public class Employment implements IEmployment {

    @EmbeddedId
    private IEmploymentKey id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Constants.M_EMPLOYMENT_SINCE, nullable = false)
    private Date since;

    @Column(name = Constants.M_EMPLOYMENT_ACTIVE, nullable = false)
    private Boolean active;

    @Override
    public IEmploymentKey getId() {
        return id;
    }

    @Override
    public void setId(IEmploymentKey employmentKey) {
        this.id = employmentKey;
    }

    @Override
    public Date getSince() {
        return since;
    }

    @Override
    public void setSince(Date since) {
        this.since = since;
    }

    @Override
    public Boolean isActive() {
        return active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
    }
}
