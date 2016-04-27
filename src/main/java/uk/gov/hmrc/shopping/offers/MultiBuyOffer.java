package uk.gov.hmrc.shopping.offers;

import java.math.BigDecimal;

/**
 * An implementation of Offer interface, that provides offers on the multiple items. It takes the buy quantity and pay quantity.
 * For instance in case of 3 for 2 offer, buy quantity would be 3 and pay quantity would be 2. Likewise, in case of 5 for 4 offer,
 * buy quantity would be 5 and pay quantity would be 4.
 */
public class MultiBuyOffer implements Offer {
    private final int buyQuantity;
    private final int payQuantity;

    public MultiBuyOffer(int buyQuantity, int payQuantity) {
        this.buyQuantity = buyQuantity;
        this.payQuantity = payQuantity;
    }

    /**
     * This method will calculate the price of the items with the offer. For instance, in case of 3 for 2 offer, if there
     * are 5 items, then it will return sum of 2(first 3 items, you pay only for 2) * unitPrice + 2(remaining 2 itmes) * unitPrice
     * @param unitPrice Price of single item
     * @param quantity number of item
     * @return price to be paid for the items after considering the offer
     */
    @Override
    public BigDecimal calculatePrice(BigDecimal unitPrice, int quantity) {
        int fullSets = quantity / buyQuantity;
        int individualItems = quantity % buyQuantity;
        BigDecimal priceForItemsWithOffer = unitPrice
                                                    .multiply(BigDecimal.valueOf(payQuantity))
                                                    .multiply(BigDecimal.valueOf(fullSets));
        BigDecimal priceForItemsOutsideOfOffer = individualItems == 0 ? BigDecimal.ZERO: unitPrice
                                                                                                .multiply(BigDecimal.valueOf(individualItems));

        return priceForItemsWithOffer.add(priceForItemsOutsideOfOffer);
    }

    @Override
    public String toString() {
        return "Offer: " + buyQuantity + " for the price of " + payQuantity;
    }

}
