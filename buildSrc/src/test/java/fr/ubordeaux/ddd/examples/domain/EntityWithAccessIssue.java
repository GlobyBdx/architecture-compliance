package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.fields.EntityID;
import fr.ubordeaux.ddd.annotations.methods.Setter;

import java.util.Objects;

import fr.ubordeaux.ddd.annotations.classes.Entity;

@Entity
public class EntityWithAccessIssue {
    @EntityID
    private String oneId;
    private int two;

    public EntityWithAccessIssue(String one) {
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

    public void accessField() {
        this.oneId.length();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof EntityWithAccessIssue)) return false;
        return this.oneId.compareTo(((EntityWithAccessIssue)other).oneId) == 0;
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
