package fr.ubordeaux.ddd.examples.domain;

import java.util.Objects;

import fr.ubordeaux.ddd.annotations.methods.Getter;
import fr.ubordeaux.ddd.annotations.methods.Setter;
import fr.ubordeaux.ddd.annotations.classes.ValueObject;
import fr.ubordeaux.ddd.annotations.fields.Immutable;

@ValueObject
public class ValueObjectB {
    @Immutable
    private String oneImm;
    @Immutable
    private int twoImm;

    public ValueObjectB(String one, int two) {
        this.setOne(one);
        this.setTwo(two);
    }

    @Setter
    private void setOne(String one) {
        this.oneImm = one;
    }

    @Setter
    private void setTwo(int two) {
        this.twoImm = two;
    }

    @Getter
    public String getOne() {
        return this.oneImm;
    }

    @Getter
    public int getTwo() {
        return this.twoImm;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ValueObjectB)) return false;
        return this.oneImm.compareTo(((ValueObjectB)other).oneImm) == 0
                && this.twoImm == ((ValueObjectB)other).twoImm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.oneImm, this.twoImm);
    }

    @Override
    public String toString() {
        return this.oneImm + this.twoImm;
    }
}
