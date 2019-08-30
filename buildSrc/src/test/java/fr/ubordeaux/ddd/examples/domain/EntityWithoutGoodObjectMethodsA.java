package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.fields.EntityID;
import fr.ubordeaux.ddd.annotations.methods.Setter;

import java.util.Objects;

import fr.ubordeaux.ddd.annotations.classes.Entity;

@Entity
public class EntityWithoutGoodObjectMethodsA {
    @EntityID
    private String oneId;
    private int two;

    public EntityWithoutGoodObjectMethodsA(String one) {
        this.setOne(one);
    }

    @Setter
    private void setOne(String one) {
        this.oneId = one;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public int getTwo() {
        return this.two;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof EntityWithoutGoodObjectMethodsA)) return false;
        return this.two == ((EntityWithoutGoodObjectMethodsA)other).two;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.two);
    }

    @Override
    public String toString() {
        return Integer.toString(this.two);
    }
}
