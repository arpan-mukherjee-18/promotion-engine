package org.arpanm18.exercise.config;

import org.arpanm18.exercise.service.PromotionEngine;
import org.arpanm18.exercise.service.PromotionEngineImpl;

public class PromotionEngineConfig {

    private final PromotionEngine promotionEngine = new PromotionEngineImpl();
}
