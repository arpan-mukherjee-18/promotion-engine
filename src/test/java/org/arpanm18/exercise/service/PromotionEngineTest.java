package org.arpanm18.exercise.service;

import org.arpanm18.exercise.config.PromotionEngineConfig;
import org.arpanm18.exercise.dto.Cart;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
        Cart cart = Cart.init().addItem("A", 1).addItem("B", 1).addItem("C", 1);
        BigDecimal expectedCostAfterPromotion = BigDecimal.valueOf(100);

        //when
        BigDecimal actualCostAfterPromotion = promotionEngineConfig.getPromotionEngine().applyPromotion(cart);

        //then
        assertEquals(expectedCostAfterPromotion, actualCostAfterPromotion);
    }
}
