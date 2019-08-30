package fr.ubordeaux.ddd.examples.domain;

import java.util.Objects;

import fr.ubordeaux.ddd.annotations.methods.Setter;
import fr.ubordeaux.ddd.annotations.classes.ValueObject;

@ValueObject
public class ValueObjectWithNonPrivateSetters {
    private String one;
    private int two;

    public ValueObjectWithNonPrivateSetters(String one, int two) {
        this.setOne(one);
        this.setTwo(two);
    }

    @Setter
    public void setOne(String one) {
        this.one = one;
    }

    @Setter
    public void setTwo(int two) {
        this.two = two;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ValueObjectWithNonPrivateSetters)) return false;
        return this.one.compareTo(((ValueObjectWithNonPrivateSetters)other).one) == 0
                && this.two == ((ValueObjectWithNonPrivateSetters)other).two;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.one, this.two);
    }

    @Override
    public String toString() {
        return this.one + this.two;
    }
}
