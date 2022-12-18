package org.arpanm18.exercise.config;

import org.arpanm18.exercise.dto.SkUnit;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class NUnitsFixedConfiguration {

    private final Map<SkUnit, Map.Entry<Integer, BigDecimal>> nUnitsFixedPrices;

    public NUnitsFixedConfiguration() {
        nUnitsFixedPrices = new ConcurrentHashMap<>();
        nUnitsFixedPrices.put(SkUnit.A, new AbstractMap.SimpleEntry<>(3, BigDecimal.valueOf(130)));
        nUnitsFixedPrices.put(SkUnit.B, new AbstractMap.SimpleEntry<>(2, BigDecimal.valueOf(45)));
    }

    public Optional<Map.Entry<Integer, BigDecimal>> getUnitPrice(SkUnit skUnit) {
        return Optional.ofNullable(this.nUnitsFixedPrices.get(skUnit));
    }
}
