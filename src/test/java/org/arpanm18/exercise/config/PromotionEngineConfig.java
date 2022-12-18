package org.arpanm18.exercise.config;

import org.arpanm18.exercise.service.NunitsFixedPricePromotionEngine;
import org.arpanm18.exercise.service.PromotionEngine;
import org.arpanm18.exercise.service.SimplePromotionEngine;

public class PromotionEngineConfig {

    private final PromotionEngine promotionEngine;

    public PromotionEngineConfig() {
        NUnitsFixedConfiguration nunitsFixedConfiguration = new NUnitsFixedConfiguration();
        SimpleUnitConfiguration simpleUnitConfiguration = new SimpleUnitConfiguration();
        SimplePromotionEngine simplePromotionEngine = new SimplePromotionEngine(simpleUnitConfiguration);
        promotionEngine = new NunitsFixedPricePromotionEngine(simplePromotionEngine, nunitsFixedConfiguration);
    }

    public PromotionEngine getPromotionEngine() {
        return promotionEngine;
    }
}
