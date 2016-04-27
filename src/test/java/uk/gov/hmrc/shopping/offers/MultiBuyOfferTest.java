package uk.gov.hmrc.shopping.offers;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class MultiBuyOfferTest {
    @Test
    public void testLessItemsThanMinimumResultsInFullPaymentForAllItems() {
        MultiBuyOffer multiBuyOffer = new MultiBuyOffer(3, 2);
        assertEquals(BigDecimal.valueOf(20.0).multiply(BigDecimal.valueOf(2)), multiBuyOffer.calculatePrice(BigDecimal.valueOf(20.0), 2));
    }

    @Test
    public void testItmesMatchingMinimumResultsInPaymentForPayQuantityOnly() {
        MultiBuyOffer multiBuyOffer = new MultiBuyOffer(3, 2);
        assertEquals(BigDecimal.valueOf(20.0).multiply(BigDecimal.valueOf(2)), multiBuyOffer.calculatePrice(BigDecimal.valueOf(20.0), 3));
    }

    @Test
    public void testMoreThanMinimumResultsInOfferPaymentForMinAndFullForOtherItems() {
        MultiBuyOffer multiBuyOffer = new MultiBuyOffer(3, 2);
        assertEquals(BigDecimal.valueOf(10.0).multiply(BigDecimal.valueOf(4)), multiBuyOffer.calculatePrice(BigDecimal.valueOf(10.0), 5));
    }

    @Test
    public void testItmesMatchingTwiceMinimumResultsInPaymentForPayQuantityOnly() {
        MultiBuyOffer multiBuyOffer = new MultiBuyOffer(3, 2);
        assertEquals(BigDecimal.valueOf(25.0).multiply(BigDecimal.valueOf(4)), multiBuyOffer.calculatePrice(BigDecimal.valueOf(25.0), 6));
    }

    @Test
    public void testMultiBuyOfferWorksWithFiveForFourOfferAsWell() {
        MultiBuyOffer multiBuyOffer = new MultiBuyOffer(5, 4);
        assertEquals(BigDecimal.valueOf(15.0).multiply(BigDecimal.valueOf(4)), multiBuyOffer.calculatePrice(BigDecimal.valueOf(15.0), 5));
    }
}