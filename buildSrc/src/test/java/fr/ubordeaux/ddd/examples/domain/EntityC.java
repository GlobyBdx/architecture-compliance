package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.fields.EntityID;
import fr.ubordeaux.ddd.annotations.methods.Getter;

import java.util.Objects;

import fr.ubordeaux.ddd.annotations.classes.Entity;

@Entity
public class EntityC {
    @EntityID
    private ValueObjectA oneId;
    private int two;

    public EntityC(ValueObjectA one) {
        this.oneId = one;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    @Getter
    public ValueObjectA getOne() {
        return this.oneId;
    }

    public int getTwo() {
        return this.two;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof EntityC)) return false;
        return this.oneId.equals(((EntityC)other).oneId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.oneId);
    }

    @Override
    public String toString() {
        return this.oneId.toString();
    }
}
