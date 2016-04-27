package uk.gov.hmrc.shopping;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

/**
 * This class implements ItemPricer and calculate the price of item by multiplying it with the quantity
 */
public class SimpleItemPricer implements ItemPricer {

    @Override
    public BigDecimal priceItem(Item item, int quantity) {
        if (quantity == 0) {
            return ZERO;
        }

        return item.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
