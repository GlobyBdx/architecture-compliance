package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.fields.EntityID;
import fr.ubordeaux.ddd.annotations.methods.Getter;

import java.util.Objects;

import fr.ubordeaux.ddd.annotations.classes.Entity;

@Entity
public class EntityA {
    @EntityID
    private final String oneId;
    private int two;

    public EntityA(String one) {
        this.oneId = one;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    @Getter
    public String getOne() {
        return this.oneId;
    }

    public int getTwo() {
        return this.two;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof EntityA)) return false;
        return this.oneId.compareTo(((EntityA)other).oneId) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.oneId);
    }

    @Override
    public String toString() {
        return this.oneId;
    }
}
