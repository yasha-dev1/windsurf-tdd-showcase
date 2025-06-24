package dst.ass1.jpa.model.impl;

import dst.ass1.jpa.model.IMoney;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * Embeddable Money value type implementation.
 */
@Embeddable
public class Money implements IMoney {

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal currencyValue;

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public BigDecimal getCurrencyValue() {
        return currencyValue;
    }

    @Override
    public void setCurrencyValue(BigDecimal currencyValue) {
        this.currencyValue = currencyValue;
    }
}
