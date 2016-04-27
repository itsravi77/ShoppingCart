package uk.gov.hmrc.shopping;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements BasketPrice interface and provides simple implementation. It depends on ItemPriceProvider to
 * provide the price of each individual item with its quantity including offers
 */
public class SimpleBasketPricer implements BasketPricer {

    private final ItemPricer itemPricer;

    /**
     * Takes ItemPricer to construct this object.
     * @param itemPricer ItemPriceProvider interface which provides the price of the individual item
     * @throws IllegalArgumentException if either ItemPriceProvider or OfferProvider is null
     */
    public SimpleBasketPricer(ItemPricer itemPricer) {
        if(itemPricer == null) {
            throw new IllegalArgumentException("ItemPricer cannot be null");
        }
        this.itemPricer = itemPricer;
    }

    @Override
    public BigDecimal priceBasket(List<String> items) {
        Map<Item, Integer> itemQuantities = new HashMap<>();

        if(items == null || items.isEmpty()) {
            return BigDecimal.ZERO;
        }

        for(String item : items) {
            itemQuantities.merge(Item.valueOf(item.toUpperCase()), 1, (integer, integer2) -> integer + integer2);
        }

        BigDecimal basketPrice = BigDecimal.ZERO;

        for (Map.Entry<Item, Integer> entry : itemQuantities.entrySet()) {
            Item item = entry.getKey();
            int quantity = entry.getValue();

            basketPrice = basketPrice.add(itemPricer.priceItem(item, quantity));
        }

        return basketPrice;
    }
}
