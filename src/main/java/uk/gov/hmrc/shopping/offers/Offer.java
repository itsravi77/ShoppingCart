package uk.gov.hmrc.shopping.offers;

import java.math.BigDecimal;

/**
 * An iterface to represent the offer on the items.
 */
public interface Offer {
    /**
     *
     * @param unitPrice unit price of the item
     * @param quantity number of items
     * @return price to be paid for the items after considering the offer
     */
    BigDecimal calculatePrice(BigDecimal unitPrice, int quantity);
}
