package dst.ass1.jpa.model.impl;

import dst.ass1.jpa.model.IEmployment;
import dst.ass1.jpa.model.IEmploymentKey;
import dst.ass1.jpa.util.Constants;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Constants.T_EMPLOYMENT)
public class Employment implements IEmployment {

    @EmbeddedId
    private IEmploymentKey id;

    @Column(name = Constants.M_EMPLOYMENT_SINCE)
    @Temporal(TemporalType.DATE)
    private Date since;

    @Column(name = Constants.M_EMPLOYMENT_ACTIVE)
    private Boolean active;

    public Employment() {
    }

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
