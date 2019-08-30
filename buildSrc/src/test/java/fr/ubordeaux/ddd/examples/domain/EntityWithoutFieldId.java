package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.classes.Entity;

@Entity
public class EntityWithoutFieldId {
    private int one;

    public EntityWithoutFieldId() {}

    public void setOne(int one) {
        this.one = one;
    }

    public int getOne() {
        return this.one;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof EntityWithoutFieldId)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "";
    }
}
