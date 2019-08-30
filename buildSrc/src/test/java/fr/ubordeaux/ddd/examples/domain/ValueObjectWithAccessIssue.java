package fr.ubordeaux.ddd.examples.domain;

import java.util.Objects;

import fr.ubordeaux.ddd.annotations.methods.Setter;
import fr.ubordeaux.ddd.annotations.classes.ValueObject;

@ValueObject
public class ValueObjectWithAccessIssue {
    private String one;
    private int two;

    public ValueObjectWithAccessIssue(String one, int two) {
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

    public void accessFields() {
        this.one.indexOf(this.two);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ValueObjectWithAccessIssue)) return false;
        return this.one.compareTo(((ValueObjectWithAccessIssue)other).one) == 0
                && this.two == ((ValueObjectWithAccessIssue)other).two;
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
