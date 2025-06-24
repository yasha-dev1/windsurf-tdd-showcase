package dst.ass1.jpa.model.impl;

import dst.ass1.jpa.model.IPlatformUser;
import dst.ass1.jpa.util.Constants;

import javax.persistence.*;

/**
 * Abstract base class for platform users (Rider and Driver).
 * Uses JOINED inheritance strategy.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "USER_TYPE")
public abstract class PlatformUser implements IPlatformUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Constants.M_RIDER_NAME, nullable = false)
    private String name;

    @Column(name = Constants.M_RIDER_TEL)
    private String tel;

    @Column(name = Constants.M_RIDER_AVG_RATING)
    private Double avgRating;

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
}
