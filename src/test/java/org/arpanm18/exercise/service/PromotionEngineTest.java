package org.arpanm18.exercise.service;

import org.arpanm18.exercise.config.PromotionEngineConfig;
import org.arpanm18.exercise.dto.Cart;
import org.arpanm18.exercise.dto.SkUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromotionEngineTest {

    private static PromotionEngineConfig promotionEngineConfig;

    @BeforeAll
    static void setUp() {
        promotionEngineConfig = new PromotionEngineConfig();
    }

    @Test
    void shouldApplySimplePromotionPriorCartCheckout() {
        //given
        Cart cart = Cart.init().addItem(SkUnit.A, 1).addItem(SkUnit.B, 1).addItem(SkUnit.C, 1);
        BigDecimal expectedCostAfterPromotion = BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP);

        //when
        BigDecimal actualCostAfterPromotion = promotionEngineConfig.getPromotionEngine().applyPromotion(cart);

        //then
        assertEquals(expectedCostAfterPromotion, actualCostAfterPromotion);
    }

    @Test
    void shouldApplyNItemsFixedPricePromotionPriorCartCheckout() {
        //given
        Cart cart = Cart.init().addItem(SkUnit.A, 5).addItem(SkUnit.B, 5).addItem(SkUnit.C, 1);
        BigDecimal expectedCostAfterPromotion = BigDecimal.valueOf(370).setScale(2, RoundingMode.HALF_UP);

        //when
        BigDecimal actualCostAfterPromotion = promotionEngineConfig.getPromotionEngine().applyPromotion(cart);

        //then
        assertEquals(expectedCostAfterPromotion, actualCostAfterPromotion);
    }

    @Test
    void shouldApplyNItemsFixedPriceAndCoupleComboPricePromotionPriorCartCheckout() {
        //given
        Cart cart = Cart.init().addItem(SkUnit.A, 3).addItem(SkUnit.B, 5).addItem(SkUnit.C, 1).addItem(SkUnit.D, 1);
        BigDecimal expectedCostAfterPromotion = BigDecimal.valueOf(280).setScale(2, RoundingMode.HALF_UP);

        //when
        BigDecimal actualCostAfterPromotion = promotionEngineConfig.getPromotionEngine().applyPromotion(cart);

        //then
        assertEquals(expectedCostAfterPromotion, actualCostAfterPromotion);
    }
}
