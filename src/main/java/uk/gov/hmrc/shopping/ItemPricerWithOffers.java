package uk.gov.hmrc.shopping;

import com.google.common.collect.ImmutableMap;
import uk.gov.hmrc.shopping.offers.BuyOneGetOneOffer;
import uk.gov.hmrc.shopping.offers.MultiBuyOffer;
import uk.gov.hmrc.shopping.offers.Offer;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the ItemPricer interface and provides the item price with the offers considered. It maintains the
 * offers statically hard-coded in the code.
 */
public class ItemPricerWithOffers implements ItemPricer {

    private final Map<Item, Offer> itemToOfferMap;

    public ItemPricerWithOffers() {
        this(ImmutableMap.of(Item.ORANGE, new MultiBuyOffer(3, 2),
                             Item.APPLE, new BuyOneGetOneOffer()));
    }

    public ItemPricerWithOffers(Map<Item, Offer> offers) {
        itemToOfferMap = new HashMap<>(offers);
    }

    /**
     * Calculates the net price of the items with the offers
     * @param item item name
     * @param quantity item quanity
     * @return price of the items for the quantity with offers included
     */
    @Override
    public BigDecimal priceItem(Item item, int quantity) {
        Offer offer = itemToOfferMap.get(item);

        if(offer != null) {
            return offer.calculatePrice(item.getPrice(), quantity);
        } else {
            return item.getPrice().multiply(BigDecimal.valueOf(quantity));
        }
    }
}
