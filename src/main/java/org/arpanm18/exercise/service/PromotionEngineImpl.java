package org.arpanm18.exercise.service;

import org.arpanm18.exercise.config.UnitConfiguration;
import org.arpanm18.exercise.dto.Cart;
import org.arpanm18.exercise.dto.SkUnit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class PromotionEngineImpl implements PromotionEngine {

    private final UnitConfiguration unitConfiguration;

    public PromotionEngineImpl(UnitConfiguration unitConfiguration) {
        this.unitConfiguration = unitConfiguration;
    }

    @Override
    public BigDecimal applyPromotion(Cart cart) {
        return cart.getCartItems().entrySet().stream().map(this::evaluateCost).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal evaluateCost(Map.Entry<SkUnit, Integer> cartEntry) {
        return this.unitConfiguration.getUnitPrice(cartEntry.getKey())
                .multiply(BigDecimal.valueOf(cartEntry.getValue())).setScale(2, RoundingMode.HALF_UP);
    }
}
