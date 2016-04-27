package uk.gov.hmrc.shopping.offers;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class BuyOneGetOneOfferTest {
    @Test
    public void testOneItemPayForOneItem() {
        BuyOneGetOneOffer bogofOffer = new BuyOneGetOneOffer();
        assertEquals(BigDecimal.valueOf(20.0), bogofOffer.calculatePrice(BigDecimal.valueOf(20.0), 1));
    }

    @Test
    public void testTwoItemsPayForOneItem() {
        BuyOneGetOneOffer bogofOffer = new BuyOneGetOneOffer();
        assertEquals(BigDecimal.valueOf(20.0), bogofOffer.calculatePrice(BigDecimal.valueOf(20.0), 2));
    }

    @Test
    public void testThreeItemPayForTwoItem() {
        BuyOneGetOneOffer bogofOffer = new BuyOneGetOneOffer();
        assertEquals(BigDecimal.valueOf(20.0).multiply(BigDecimal.valueOf(2)), bogofOffer.calculatePrice(BigDecimal.valueOf(20.0), 3));
    }

    @Test
    public void testFourItemsPayForTwoItem() {
        BuyOneGetOneOffer bogofOffer = new BuyOneGetOneOffer();
        assertEquals(BigDecimal.valueOf(20.0).multiply(BigDecimal.valueOf(2)), bogofOffer.calculatePrice(BigDecimal.valueOf(20.0), 4));
    }
}