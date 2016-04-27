package uk.gov.hmrc.shopping;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleBasketPricerTest {

    private ItemPricer itemPricer;
    private BasketPricer basketPricer;

    @Before
    public void beforeEveryMethod() {
        itemPricer = mock(ItemPricer.class);
        basketPricer = new SimpleBasketPricer(itemPricer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreationThrowsExceptionWhenNullItemPricerIsPassed() {
        new SimpleBasketPricer(null);
    }

    @Test
    public void testBasketReturnsZeroWhenItIsEmpty() {
        assertEquals(BigDecimal.ZERO, basketPricer.priceBasket(Collections.emptyList()));
        Mockito.verifyZeroInteractions(itemPricer);
    }

    @Test
    public void testBasketReturnsZeroWhenItemsIsNull() {
        assertEquals(BigDecimal.ZERO, basketPricer.priceBasket(null));
        Mockito.verifyZeroInteractions(itemPricer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidItemThrowsException() {
        basketPricer.priceBasket(Collections.singletonList("NonExistent"));
    }

    @Test
    public void testMultipleItemsGroupedTogether() {
        when(itemPricer.priceItem(Item.APPLE, 2)).thenReturn(BigDecimal.valueOf(120, 2));
        when(itemPricer.priceItem(Item.ORANGE, 2)).thenReturn(BigDecimal.valueOf(50, 2));

        BigDecimal actualPrice = basketPricer.priceBasket(Arrays.asList("Apple", "Orange", "Apple", "Orange"));
        Assert.assertEquals(BigDecimal.valueOf(170, 2), actualPrice);
    }

    @Test
    public void testIndividualItemsPricedIndividually() {
        when(itemPricer.priceItem(Item.APPLE, 1)).thenReturn(BigDecimal.valueOf(60, 2));
        when(itemPricer.priceItem(Item.ORANGE, 1)).thenReturn(BigDecimal.valueOf(25, 2));

        BigDecimal actualPrice = basketPricer.priceBasket(Arrays.asList("Apple", "Orange"));
        Assert.assertEquals(BigDecimal.valueOf(85, 2), actualPrice);
    }
}