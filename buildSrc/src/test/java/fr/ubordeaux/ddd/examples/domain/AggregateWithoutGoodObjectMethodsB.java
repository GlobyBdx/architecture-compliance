package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.fields.EntityID;
import fr.ubordeaux.ddd.annotations.methods.Setter;

import java.util.Objects;

import fr.ubordeaux.ddd.annotations.classes.Aggregate;

@Aggregate
public class AggregateWithoutGoodObjectMethodsB {
    @EntityID
    private String oneId;
    private ValueObjectB two;
    private ValueObjectC three;
    private int four;

    public AggregateWithoutGoodObjectMethodsB(String one) {
        this.setOne(one);
    }

    @Setter
    private void setOne(String one) {
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
        if (!(other instanceof AggregateWithoutGoodObjectMethodsB)) return false;
        return this.two.equals(((AggregateWithoutGoodObjectMethodsB)other).two);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.two);
    }

    @Override
    public String toString() {
        return this.two.toString();
    }
}
