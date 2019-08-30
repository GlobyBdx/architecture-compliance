package fr.ubordeaux.ddd.examples.domain;

import java.util.Objects;

import fr.ubordeaux.ddd.annotations.methods.Getter;
import fr.ubordeaux.ddd.annotations.methods.Setter;
import fr.ubordeaux.ddd.annotations.classes.ValueObject;

@ValueObject
public class ValueObjectWithGetterIssue {
    private String one;
    private int two;

    public ValueObjectWithGetterIssue(String one, int two) {
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
        if (!(other instanceof ValueObjectWithGetterIssue)) return false;
        return this.one.compareTo(((ValueObjectWithGetterIssue)other).one) == 0
                && this.two == ((ValueObjectWithGetterIssue)other).two;
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
