package uk.gov.hmrc.shopping.integration;

import org.junit.Test;
import uk.gov.hmrc.shopping.Item;
import uk.gov.hmrc.shopping.ItemPricerWithOffers;
import uk.gov.hmrc.shopping.SimpleBasketPricer;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static junit.framework.TestCase.assertEquals;

public class BasketPriceWithOffersIntegrationTest {

    @Test
    public void testBasketReturnsZeroWhenItIsEmpty() {
        SimpleBasketPricer basketPricer = new SimpleBasketPricer(new ItemPricerWithOffers());
        assertEquals(BigDecimal.ZERO, basketPricer.priceBasket(Collections.emptyList()));
    }

    @Test
    public void testBasketReturnsZeroWhenItemsIsNull() {
        SimpleBasketPricer basketPricer = new SimpleBasketPricer(new ItemPricerWithOffers());
        assertEquals(BigDecimal.ZERO, basketPricer.priceBasket(null));
    }

    @Test
    public void testBasketPriceWithOffer() {
        SimpleBasketPricer basketPricer = new SimpleBasketPricer(new ItemPricerWithOffers());
        BigDecimal expectedAmount = Item.APPLE.getPrice()
                                    .add(
                                            Item.ORANGE.getPrice()
                                                    .multiply(BigDecimal.valueOf(2))
                                    );
        assertEquals(expectedAmount, basketPricer.priceBasket(Arrays.asList("Apple", "Orange", "Apple", "Orange", "Orange")));
    }

    @Test
    public void testBasketPriceNoQualifyingOffers() {
        SimpleBasketPricer basketPricer = new SimpleBasketPricer(new ItemPricerWithOffers());
        BigDecimal expectedAmount = Item.APPLE.getPrice()
                .add(
                        Item.ORANGE.getPrice()
                                .multiply(BigDecimal.valueOf(2))
                );
        assertEquals(expectedAmount, basketPricer.priceBasket(Arrays.asList("Apple", "Orange", "Orange")));
    }

    @Test
    public void testFourOrangePayForThree() {
        SimpleBasketPricer basketPricer = new SimpleBasketPricer(new ItemPricerWithOffers());
        BigDecimal expectedAmount = Item.ORANGE.getPrice()
                                                .multiply(BigDecimal.valueOf(3)); // 3 for 2 on first 3 items, remaining one full price
        assertEquals(expectedAmount, basketPricer.priceBasket(Arrays.asList("Orange", "Orange", "Orange", "Orange")));
    }

    @Test
    public void testThreeApplesPayForTwo() {
        SimpleBasketPricer basketPricer = new SimpleBasketPricer(new ItemPricerWithOffers());
        BigDecimal expectedAmount = Item.APPLE.getPrice()
                .multiply(BigDecimal.valueOf(2)); //buy one get one free on first 2, remaining one full price
        assertEquals(expectedAmount, basketPricer.priceBasket(Arrays.asList("Apple", "Apple", "Apple")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfInvalidItemIsPresentInBasket() {
        SimpleBasketPricer basketPricer = new SimpleBasketPricer(new ItemPricerWithOffers());
        basketPricer.priceBasket(Collections.singletonList("NonExistentItem"));
    }

}
