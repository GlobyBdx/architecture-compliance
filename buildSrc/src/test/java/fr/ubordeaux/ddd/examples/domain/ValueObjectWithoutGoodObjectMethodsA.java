package fr.ubordeaux.ddd.examples.domain;

import java.util.Objects;

import fr.ubordeaux.ddd.annotations.methods.Setter;
import fr.ubordeaux.ddd.annotations.classes.ValueObject;

@ValueObject
public class ValueObjectWithoutGoodObjectMethodsA {
    private String one;
    @SuppressWarnings("unused")
    private int two;

    public ValueObjectWithoutGoodObjectMethodsA(String one, int two) {
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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ValueObjectWithoutGoodObjectMethodsA)) return false;
        return this.one.compareTo(((ValueObjectWithoutGoodObjectMethodsA)other).one) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.one);
    }

    @Override
    public String toString() {
        return this.one;
    }
}
