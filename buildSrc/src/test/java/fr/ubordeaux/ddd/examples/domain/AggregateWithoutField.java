package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.classes.Aggregate;

@Aggregate
public class AggregateWithoutField {
    public AggregateWithoutField(String one) {}

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ValueObjectWithoutField)) return false;
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
