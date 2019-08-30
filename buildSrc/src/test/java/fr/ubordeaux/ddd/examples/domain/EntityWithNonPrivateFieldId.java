package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.fields.EntityID;
import fr.ubordeaux.ddd.annotations.methods.Setter;

import java.util.Objects;

import fr.ubordeaux.ddd.annotations.classes.Entity;

@Entity
public class EntityWithNonPrivateFieldId {
    @EntityID
    public String oneId;
    private int two;

    public EntityWithNonPrivateFieldId(String one) {
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
        if (!(other instanceof EntityWithNonPrivateFieldId)) return false;
        return this.oneId.compareTo(((EntityWithNonPrivateFieldId)other).oneId) == 0;
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
