package fr.ubordeaux.ddd.examples.domain;

import java.util.Objects;

import fr.ubordeaux.ddd.annotations.methods.Getter;
import fr.ubordeaux.ddd.annotations.classes.ValueObject;

@ValueObject
public class ValueObjectA {
    private final String one;
    private final int two;

    public ValueObjectA(String one, int two) {
        this.one = one;
        this.two = two;
    }

    @Getter
    public String getOne() {
        return this.one;
    }

    @Getter
    public int getTwo() {
        return this.two;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ValueObjectA)) return false;
        return this.one.compareTo(((ValueObjectA)other).one) == 0
                && this.two == ((ValueObjectA)other).two;
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
