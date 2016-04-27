package uk.gov.hmrc.shopping.offers;

/**
 * An implementation of Offer interface, that provides Buy One Get One Offer. It exgtends MultiBuyOffer class
 */
public class BuyOneGetOneOffer extends MultiBuyOffer {
    public BuyOneGetOneOffer() {
        super(2, 1);
    }

    @Override
    public String toString() {
        return "Offer: Buy one get one free";
    }
}
