package org.arpanm18.exercise.config;

import org.arpanm18.exercise.dto.SkUnit;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class UnitConfiguration {

    private final Map<SkUnit, BigDecimal> unitPriceConfiguration;

    public UnitConfiguration() {
        unitPriceConfiguration = new ConcurrentHashMap<>();
        unitPriceConfiguration.put(SkUnit.A, BigDecimal.valueOf(50));
        unitPriceConfiguration.put(SkUnit.B, BigDecimal.valueOf(30));
        unitPriceConfiguration.put(SkUnit.C, BigDecimal.valueOf(20));
        unitPriceConfiguration.put(SkUnit.D, BigDecimal.valueOf(15));
    }

    public BigDecimal getUnitPrice(SkUnit skUnit) {
        return Optional.ofNullable(this.unitPriceConfiguration.get(skUnit)).orElseThrow(IllegalArgumentException::new);
    }

}
