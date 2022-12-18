package org.arpanm18.exercise.config;

import org.arpanm18.exercise.service.PromotionEngine;
import org.arpanm18.exercise.service.SimplePromotionEngine;

public class PromotionEngineConfig {

    private final PromotionEngine promotionEngine;

    public PromotionEngineConfig() {
        promotionEngine = new SimplePromotionEngine(new UnitConfiguration());
    }

    public PromotionEngine getPromotionEngine() {
        return promotionEngine;
    }
}
