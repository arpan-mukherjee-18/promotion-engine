package org.arpanm18.exercise.service;

import org.arpanm18.exercise.config.NUnitsFixedConfiguration;
import org.arpanm18.exercise.dto.Cart;
import org.arpanm18.exercise.dto.SkUnit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;

public class NunitsFixedPricePromotionEngine implements PromotionEngine {

    private final PromotionEngine nextEngine;

    private final NUnitsFixedConfiguration nunitsFixedConfiguration;

    public NunitsFixedPricePromotionEngine(PromotionEngine nextEngine, NUnitsFixedConfiguration nunitsFixedConfiguration) {
        this.nextEngine = nextEngine;
        this.nunitsFixedConfiguration = nunitsFixedConfiguration;
    }

    @Override
    public BigDecimal applyPromotion(Cart cart) {
        BigDecimal promotionValue = cart.getCartItems().entrySet().stream().map(this::evaluateCost).reduce(BigDecimal.ZERO, BigDecimal::add);
        return promotionValue.add(nextEngine.applyPromotion(cart));
    }

    private BigDecimal evaluateCost(Map.Entry<SkUnit, Integer> cartEntry) {
        Optional<Map.Entry<Integer, BigDecimal>> promotionalPrice = nunitsFixedConfiguration.getUnitPrice(cartEntry.getKey());
        BigDecimal priceAfterPromotion = BigDecimal.ZERO;
        if (promotionalPrice.isPresent()) {
            Map.Entry<Integer, BigDecimal> constraint = promotionalPrice.get();
            if (cartEntry.getValue().compareTo(constraint.getKey()) > 0) {
                int numberOfPromotions = cartEntry.getValue() / constraint.getKey();
                int remaining = cartEntry.getValue() % constraint.getKey();
                priceAfterPromotion = constraint.getValue().multiply(BigDecimal.valueOf(numberOfPromotions)).setScale(2, RoundingMode.HALF_UP);
                cartEntry.setValue(remaining);
            }
        }
        return priceAfterPromotion;
    }

}
