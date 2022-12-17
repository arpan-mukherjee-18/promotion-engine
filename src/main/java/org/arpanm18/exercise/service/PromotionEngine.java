package org.arpanm18.exercise.service;

import org.arpanm18.exercise.dto.Cart;

import java.math.BigDecimal;

public interface PromotionEngine {

    BigDecimal applyPromotion(Cart cart);
}
