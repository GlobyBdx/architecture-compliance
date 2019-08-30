package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.methods.Setter;
import fr.ubordeaux.ddd.annotations.classes.ValueObject;

@ValueObject
public class ValueObjectWithoutGoodObjectMethodsB {
    private String one;
    private int two;

    public ValueObjectWithoutGoodObjectMethodsB(String one, int two) {
        this.setOne(one);
        this.setTwo(two);
    }

    @Setter
    private void setOne(String one) {
        this.one = one;
    }

    @Setter
    private void setTwo(int two) {
        this.two = two;
    }

    public boolean equals(ValueObjectWithoutGoodObjectMethodsB other) {
        return this.one.compareTo(((ValueObjectWithoutGoodObjectMethodsB)other).one) == 0
                && this.two == ((ValueObjectWithoutGoodObjectMethodsB)other).two;
    }

    public int hashCode(int hashCode) {
        return hashCode;
    }

    public String toString(String toString) {
        return toString;
    }
}
