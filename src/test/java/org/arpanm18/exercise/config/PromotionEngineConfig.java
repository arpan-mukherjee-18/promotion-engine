package org.arpanm18.exercise.config;

import org.arpanm18.exercise.service.PromotionEngine;
import org.arpanm18.exercise.service.PromotionEngineImpl;

public class PromotionEngineConfig {

    private final PromotionEngine promotionEngine;

    public PromotionEngineConfig() {
        promotionEngine = new PromotionEngineImpl();
    }

    public PromotionEngine getPromotionEngine() {
        return promotionEngine;
    }
}
