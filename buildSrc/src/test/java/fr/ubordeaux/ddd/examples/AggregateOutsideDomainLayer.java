package fr.ubordeaux.ddd.examples;

import fr.ubordeaux.ddd.annotations.fields.EntityID;
import fr.ubordeaux.ddd.annotations.methods.Getter;
import fr.ubordeaux.ddd.examples.domain.ValueObjectB;
import fr.ubordeaux.ddd.examples.domain.ValueObjectC;

import java.util.Objects;

import fr.ubordeaux.ddd.annotations.classes.Aggregate;

@Aggregate
public class AggregateOutsideDomainLayer {
    @EntityID
    private String oneId;
    private ValueObjectB two;
    private ValueObjectC three;
    private int four;

    public AggregateOutsideDomainLayer(String one) {
        this.oneId = one;
    }

    public void setTwo(ValueObjectB two) {
        this.two = two;
    }

    public void setThree(ValueObjectC three) {
        this.three = three;
    }

    public void setFour(int four) {
        this.four = four;
    }

    @Getter
    public String getOne() {
        return this.oneId;
    }

    public ValueObjectB getTwo() {
        return this.two;
    }

    public ValueObjectC getThree() {
        return this.three;
    }

    public int getFour() {
        return this.four;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AggregateOutsideDomainLayer)) return false;
        return this.oneId.compareTo(((AggregateOutsideDomainLayer)other).oneId) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.oneId);
    }

    @Override
    public String toString() {
        return this.oneId;
    }
}
