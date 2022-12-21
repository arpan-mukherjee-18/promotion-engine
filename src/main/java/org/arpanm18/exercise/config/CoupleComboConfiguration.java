package org.arpanm18.exercise.config;

import org.arpanm18.exercise.dto.SkUnit;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class CoupleComboConfiguration {

    private final Set<CoupleCombo> coupleComboPrices;

    public CoupleComboConfiguration() {
        coupleComboPrices = new CopyOnWriteArraySet<>();
        coupleComboPrices.add(new CoupleCombo(SkUnit.C, SkUnit.D, BigDecimal.valueOf(30)));
    }

    public Set<CoupleCombo> getCoupleComboPrices() {
        return coupleComboPrices;
    }

    public record CoupleCombo(SkUnit sku1, SkUnit sku2, BigDecimal price) {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CoupleCombo that = (CoupleCombo) o;
            return (sku1 == that.sku1 && sku2 == that.sku2)
                    || (sku1 == that.sku2 && sku2 == that.sku1);
        }

        @Override
        public int hashCode() {
            return Objects.hash(sku1, sku2);
        }
    }
}
