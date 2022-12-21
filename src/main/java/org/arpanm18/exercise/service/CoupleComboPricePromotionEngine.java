package org.arpanm18.exercise.service;

import org.arpanm18.exercise.config.CoupleComboConfiguration;
import org.arpanm18.exercise.dto.Cart;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CoupleComboPricePromotionEngine implements PromotionEngine {

    private final PromotionEngine nextEngine;

    private final CoupleComboConfiguration coupleComboConfiguration;

    public CoupleComboPricePromotionEngine(PromotionEngine nextEngine, CoupleComboConfiguration coupleComboConfiguration) {
        this.nextEngine = nextEngine;
        this.coupleComboConfiguration = coupleComboConfiguration;
    }

    @Override
    public BigDecimal applyPromotion(Cart cart) {
        BigDecimal promotionValue = this.coupleComboConfiguration.getCoupleComboPrices().stream().map(coupleCombo -> this.evaluateCost(coupleCombo, cart)).reduce(BigDecimal.ZERO, BigDecimal::add);
        return promotionValue.add(nextEngine.applyPromotion(cart));
    }

    private BigDecimal evaluateCost(CoupleComboConfiguration.CoupleCombo coupleCombo, Cart cart) {
        if (cart.getCartItems().containsKey(coupleCombo.sku1()) && cart.getCartItems().containsKey(coupleCombo.sku2())) {
            Integer comboUnit1 = cart.getCartItems().get(coupleCombo.sku1());
            Integer comboUnit2 = cart.getCartItems().get(coupleCombo.sku2());
            int numberOfPromotions = Integer.min(comboUnit2, comboUnit1);
            BigDecimal priceAfterPromotion = coupleCombo.price().multiply(BigDecimal.valueOf(numberOfPromotions)).setScale(2, RoundingMode.HALF_UP);
            cart.getCartItems().put(coupleCombo.sku1(), comboUnit1 - numberOfPromotions);
            cart.getCartItems().put(coupleCombo.sku2(), comboUnit2 - numberOfPromotions);
            return priceAfterPromotion;
        }
        return BigDecimal.ZERO;
    }

}
