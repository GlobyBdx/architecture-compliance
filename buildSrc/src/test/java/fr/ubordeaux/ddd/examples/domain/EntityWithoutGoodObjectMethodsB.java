package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.fields.EntityID;
import fr.ubordeaux.ddd.annotations.methods.Setter;

import fr.ubordeaux.ddd.annotations.classes.Entity;

@Entity
public class EntityWithoutGoodObjectMethodsB {
    @EntityID
    private String oneId;
    private int two;

    public EntityWithoutGoodObjectMethodsB(String one) {
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

    public boolean equals(EntityWithoutGoodObjectMethodsB other) {
        return this.oneId.compareTo(((EntityWithoutGoodObjectMethodsB)other).oneId) == 0;
    }

    public int hashCode(int hashCode) {
        return hashCode;
    }

    public String toString(String toString) {
        return toString;
    }
}
