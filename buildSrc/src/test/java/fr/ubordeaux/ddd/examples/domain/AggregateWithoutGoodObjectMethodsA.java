package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.fields.EntityID;
import fr.ubordeaux.ddd.annotations.methods.Setter;

import fr.ubordeaux.ddd.annotations.classes.Aggregate;

@Aggregate
public class AggregateWithoutGoodObjectMethodsA {
    @EntityID
    private String oneId;
    private ValueObjectB two;
    private ValueObjectC three;
    private int four;

    public AggregateWithoutGoodObjectMethodsA(String one) {
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

    public boolean equals(AggregateWithoutGoodObjectMethodsA other) {
        return this.oneId.compareTo(((AggregateWithoutGoodObjectMethodsA)other).oneId) == 0;
    }

    public int hashCode(int hashCode) {
        return hashCode;
    }

    public String toString(String toString) {
        return toString;
    }
}
