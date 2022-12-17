package org.arpanm18.exercise.config;

import org.arpanm18.exercise.service.PromotionEngine;
import org.arpanm18.exercise.service.PromotionEngineImpl;

public class PromotionEngineConfig {

    private final PromotionEngine promotionEngine;

    public PromotionEngineConfig() {
        UnitConfiguration unitConfiguration = new UnitConfiguration();
        promotionEngine = new PromotionEngineImpl(unitConfiguration);
    }

    public PromotionEngine getPromotionEngine() {
        return promotionEngine;
    }
}
