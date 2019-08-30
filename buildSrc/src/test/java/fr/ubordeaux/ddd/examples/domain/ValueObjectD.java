package fr.ubordeaux.ddd.examples.domain;

import java.util.Objects;

import fr.ubordeaux.ddd.annotations.methods.Getter;
import fr.ubordeaux.ddd.annotations.classes.ValueObject;

@ValueObject
public class ValueObjectD {
    private ValueObjectA one;
    private ValueObjectA two;

    public ValueObjectD(ValueObjectA one, ValueObjectA two) {
        this.one = one;
        this.two = two;
    }

    @Getter
    public ValueObjectA getOne() {
        return this.one;
    }

    @Getter
    public ValueObjectA getTwo() {
        return this.two;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ValueObjectD)) return false;
        return this.one.equals(((ValueObjectD)other).one)
                && this.two.equals(((ValueObjectD)other).two);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.one, this.two);
    }

    @Override
    public String toString() {
        return this.one.toString() + this.two.toString();
    }
}
