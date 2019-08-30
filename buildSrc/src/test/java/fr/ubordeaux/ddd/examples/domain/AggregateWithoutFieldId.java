package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.classes.Aggregate;

@Aggregate
public class AggregateWithoutFieldId {
    private int one;

    public AggregateWithoutFieldId() {}

    public void setOne(int one) {
        this.one = one;
    }

    public int getOne() {
        return this.one;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AggregateWithoutFieldId)) return false;
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
