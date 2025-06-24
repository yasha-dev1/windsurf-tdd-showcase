package dst.ass1.jpa.model.impl;

import dst.ass1.jpa.model.IMoney;
import dst.ass1.jpa.model.ITripReceipt;
import dst.ass1.jpa.util.Constants;

import javax.persistence.*;

/**
 * TripReceipt entity implementation with embedded Money value types.
 */
@Entity
@Table(name = Constants.T_TRIP_RECEIPT)
public class TripReceipt implements ITripReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private IMoney fare;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = Constants.AO_NAME_TIP_CURRENCY, column = @Column(name = Constants.AO_COLUMN_NAME_TIP_CURRENCY)),
        @AttributeOverride(name = Constants.AO_NAME_TIP_CURRENCY_VALUE, column = @Column(name = Constants.AO_COLUMN_NAME_TIP_CURRENCY_VALUE))
    })
    private IMoney tip;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public IMoney getFare() {
        return fare;
    }

    @Override
    public void setFare(IMoney fare) {
        this.fare = fare;
    }

    @Override
    public IMoney getTip() {
        return tip;
    }

    @Override
    public void setTip(IMoney tip) {
        this.tip = tip;
    }
}
