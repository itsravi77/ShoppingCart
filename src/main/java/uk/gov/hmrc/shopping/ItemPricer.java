package uk.gov.hmrc.shopping;

import java.math.BigDecimal;

/**
 * An interface that has methods to calculate the price of item for the specified quantity
 */
public interface ItemPricer {
    /**
     * The method calculates the price of the item for the specified quantity
     * @param item item name
     * @param quantity item quanity
     * @return BigDecimal price of the item for the quantity passed
     */
    BigDecimal priceItem(Item item, int quantity);
}
