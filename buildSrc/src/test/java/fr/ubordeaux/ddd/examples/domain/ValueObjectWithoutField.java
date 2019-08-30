package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.classes.ValueObject;

@ValueObject
public class ValueObjectWithoutField {
    public ValueObjectWithoutField() {}

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
