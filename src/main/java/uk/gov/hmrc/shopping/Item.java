package uk.gov.hmrc.shopping;

import java.math.BigDecimal;

/**
 * Item enum
 */
public enum Item {
    APPLE(BigDecimal.valueOf(60, 2)),
    ORANGE(BigDecimal.valueOf(25, 2));

    private final BigDecimal price;

    Item(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
