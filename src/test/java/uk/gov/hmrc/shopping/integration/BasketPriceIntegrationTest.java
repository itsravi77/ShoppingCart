package uk.gov.hmrc.shopping.integration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.gov.hmrc.shopping.BasketPricer;
import uk.gov.hmrc.shopping.Item;
import uk.gov.hmrc.shopping.SimpleBasketPricer;
import uk.gov.hmrc.shopping.SimpleItemPricer;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static junit.framework.TestCase.assertEquals;

public class BasketPriceIntegrationTest {

    private BasketPricer basketPricer;

    @Before
    public void beforeEachMethod() {
        basketPricer = new SimpleBasketPricer(new SimpleItemPricer());
    }

    @Test
    public void testBasketReturnsZeroWhenItIsEmpty() {
        assertEquals(BigDecimal.ZERO, basketPricer.priceBasket(Collections.emptyList()));
    }

    @Test
    public void testBasketReturnsZeroWhenItemsIsNull() {
        assertEquals(BigDecimal.ZERO, basketPricer.priceBasket(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidItemThrowsException() {
        basketPricer.priceBasket(Collections.singletonList("NonExistent"));
    }

    @Test
    public void testMultipleItemsGroupedTogether() {
        BigDecimal actualPrice = basketPricer.priceBasket(Arrays.asList("Apple", "Orange", "Apple", "Orange", "Orange"));
        BigDecimal expectedPrice = Item.APPLE.getPrice().multiply(BigDecimal.valueOf(2)).add(Item.ORANGE.getPrice().multiply(BigDecimal.valueOf(3)));
        Assert.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testIndividualItemsPricedIndividually() {
        BigDecimal actualPrice = basketPricer.priceBasket(Arrays.asList("Apple", "Orange"));
        BigDecimal expectedPrice = Item.APPLE.getPrice().add(Item.ORANGE.getPrice());
        Assert.assertEquals(expectedPrice, actualPrice);
    }

}
