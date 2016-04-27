package uk.gov.hmrc.shopping;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class SimpleItemPricerTest {

    private ItemPricer itemPricer;
    @Before
    public void beforeEachMethod() {
        itemPricer = new SimpleItemPricer();
    }

    @Test
    public void testPriceItemReturnsZeroIfQuantityIsZero() {
        BigDecimal actualPrice = itemPricer.priceItem(Item.APPLE, 0);
        assertEquals("Expecting zero price", BigDecimal.ZERO, actualPrice);
    }

    @Test
    public void testPriceItemReturnsPriceOfSingleAppleWhenQuantityIsOne() {
        BigDecimal actualPrice = itemPricer.priceItem(Item.APPLE, 1);
        assertEquals("Expecting zero price", Item.APPLE.getPrice(), actualPrice);
    }

    @Test
    public void testPriceItemReturnsRightPriceOfMultipleApples() {
        BigDecimal actualPrice = itemPricer.priceItem(Item.APPLE, 5);
        assertEquals("Expecting zero price", Item.APPLE.getPrice().multiply(BigDecimal.valueOf(5)), actualPrice);
    }

    @Test
    public void testPriceItemReturnsPriceOfSingleOrangeWhenQuantityIsOne() {
        BigDecimal actualPrice = itemPricer.priceItem(Item.ORANGE, 1);
        assertEquals("Expecting zero price", Item.ORANGE.getPrice(), actualPrice);
    }
}