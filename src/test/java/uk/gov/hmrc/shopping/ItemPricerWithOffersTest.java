package uk.gov.hmrc.shopping;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import uk.gov.hmrc.shopping.offers.MultiBuyOffer;
import uk.gov.hmrc.shopping.offers.Offer;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ItemPricerWithOffersTest {

    private ItemPricerWithOffers itemPricerWithOffers;

    @Test
    public void testNoOfferForAnyItemReturnsFullPrice() {
        itemPricerWithOffers = new ItemPricerWithOffers(new HashMap<Item, Offer>());
        BigDecimal expectedPrice = Item.APPLE.getPrice().multiply(BigDecimal.valueOf(5));
        BigDecimal actualPrice = itemPricerWithOffers.priceItem(Item.APPLE, 5);
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testBogofOnApplesWithAllMatchingOffers() {
        itemPricerWithOffers = new ItemPricerWithOffers();
        //4 items pay for 2 as per buy one get one free offer
        BigDecimal expectedPrice = Item.APPLE.getPrice().multiply(BigDecimal.valueOf(2));
        BigDecimal actualPrice = itemPricerWithOffers.priceItem(Item.APPLE, 4);
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testBogofOnApplesWithFewMatchingOffers() {
        itemPricerWithOffers = new ItemPricerWithOffers();
        //5 items, but pay for 3 as 4 will qualify for buy one get one free, remaining one will be on it's own
        BigDecimal expectedPrice = Item.APPLE.getPrice().multiply(BigDecimal.valueOf(3));
        BigDecimal actualPrice = itemPricerWithOffers.priceItem(Item.APPLE, 5);
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void test2For2OnOrangesWithAllMatchingOffers() {
        itemPricerWithOffers = new ItemPricerWithOffers();
        //3 items, pay for 2 as per 3 for 2 offer
        BigDecimal expectedPrice = Item.APPLE.getPrice().multiply(BigDecimal.valueOf(2));
        BigDecimal actualPrice = itemPricerWithOffers.priceItem(Item.APPLE, 3);
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void test3For2OnOrangesWithOnlyFewMatchingOffers() {
        itemPricerWithOffers = new ItemPricerWithOffers();
        //5 items, but pay for 4 as 3 will qualify for 3 for the price of 2 offers, remaining 2 will be on it's own
        BigDecimal expectedPrice = Item.APPLE.getPrice().multiply(BigDecimal.valueOf(3));
        BigDecimal actualPrice = itemPricerWithOffers.priceItem(Item.APPLE, 5);
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void test4For3PriceOfferOnApples() {
        itemPricerWithOffers = new ItemPricerWithOffers(ImmutableMap.of(Item.APPLE, new MultiBuyOffer(4, 3)));
        //4 items, but pay for 3 as the offer is 4 for 3 here
        BigDecimal expectedPrice = Item.APPLE.getPrice().multiply(BigDecimal.valueOf(3));
        BigDecimal actualPrice = itemPricerWithOffers.priceItem(Item.APPLE, 4);
        assertEquals(expectedPrice, actualPrice);
    }
}