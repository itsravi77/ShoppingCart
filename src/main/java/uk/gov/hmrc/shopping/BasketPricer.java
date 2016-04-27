package uk.gov.hmrc.shopping;

import java.math.BigDecimal;
import java.util.List;

/**
 * An interface for calculating the price of the basket. It takes the items as the input and returns the total price of
 * the basket with all offers considered.
 */
public interface BasketPricer {
    /**
     * This method takes the item list and calculates the price of the basket
     * @param items list of items
     * @return the price of the basket
     * @throws IllegalArgumentException if any item in the basket is invalid
     */
    BigDecimal priceBasket(List<String> items);
}
