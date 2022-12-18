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

    private static BigDecimal evaluateCost(Map.Entry<SkUnit, Integer> cartEntry, NUnitsFixedConfiguration.Constraint constraint) {
        if (cartEntry.getValue().compareTo(constraint.count()) < 0) return BigDecimal.ZERO;
        int numberOfPromotions = cartEntry.getValue() / constraint.count();
        int remaining = cartEntry.getValue() % constraint.count();
        BigDecimal priceAfterPromotion = constraint.price().multiply(BigDecimal.valueOf(numberOfPromotions)).setScale(2, RoundingMode.HALF_UP);
        cartEntry.setValue(remaining);
        return priceAfterPromotion;
    }

    private BigDecimal evaluateCost(Map.Entry<SkUnit, Integer> cartEntry) {
        Optional<NUnitsFixedConfiguration.Constraint> promotionalPrice = nunitsFixedConfiguration.getUnitPrice(cartEntry.getKey());
        return promotionalPrice.isPresent() ? evaluateCost(cartEntry, promotionalPrice.get()) : BigDecimal.ZERO;
    }

}
