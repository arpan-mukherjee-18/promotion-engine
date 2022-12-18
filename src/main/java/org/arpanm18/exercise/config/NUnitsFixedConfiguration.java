package org.arpanm18.exercise.config;

import org.arpanm18.exercise.dto.SkUnit;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class NUnitsFixedConfiguration {

    private final Map<SkUnit, Constraint> nUnitsFixedPrices;

    public NUnitsFixedConfiguration() {
        nUnitsFixedPrices = new ConcurrentHashMap<>();
        nUnitsFixedPrices.put(SkUnit.A, new Constraint(3, BigDecimal.valueOf(130)));
        nUnitsFixedPrices.put(SkUnit.B, new Constraint(2, BigDecimal.valueOf(45)));
    }

    public Optional<Constraint> getUnitPrice(SkUnit skUnit) {
        return Optional.ofNullable(this.nUnitsFixedPrices.get(skUnit));
    }

    public record Constraint(int count, BigDecimal price) {
    }

}
