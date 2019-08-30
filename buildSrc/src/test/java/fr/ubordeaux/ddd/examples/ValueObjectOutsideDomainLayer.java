package fr.ubordeaux.ddd.examples;

import java.util.Objects;

import fr.ubordeaux.ddd.annotations.methods.Setter;
import fr.ubordeaux.ddd.annotations.classes.ValueObject;

@ValueObject
public class ValueObjectOutsideDomainLayer {
    private String one;
    private int two;

    public ValueObjectOutsideDomainLayer(String one, int two) {
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
        if (!(other instanceof ValueObjectOutsideDomainLayer)) return false;
        return this.one.compareTo(((ValueObjectOutsideDomainLayer)other).one) == 0
                && this.two == ((ValueObjectOutsideDomainLayer)other).two;
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
