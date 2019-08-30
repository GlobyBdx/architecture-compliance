package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.fields.EntityID;
import fr.ubordeaux.ddd.annotations.methods.Getter;

import java.util.Objects;

import fr.ubordeaux.ddd.annotations.classes.Entity;

@Entity
public class EntityD {
    @EntityID
    private EntityA oneId;
    private int two;

    public EntityD(EntityA one) {
        this.oneId = one;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    @Getter
    public EntityA getOne() {
        return this.oneId;
    }

    public int getTwo() {
        return this.two;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof EntityD)) return false;
        return this.oneId.equals(((EntityD)other).oneId);
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
