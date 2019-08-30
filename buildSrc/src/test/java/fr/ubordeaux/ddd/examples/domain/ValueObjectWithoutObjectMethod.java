package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.methods.Setter;
import fr.ubordeaux.ddd.annotations.classes.ValueObject;

@ValueObject
public class ValueObjectWithoutObjectMethod {
    @SuppressWarnings("unused")
    private String one;
    @SuppressWarnings("unused")
    private int two;

    public ValueObjectWithoutObjectMethod(String one, int two) {
        this.setOne(one);
        this.setTwo(two);
    }

    @Setter
    private void setOne(String one) {
        this.one = one;
    }

    @Setter
    private void setTwo(int two) {
        this.two = two;
    }
}
