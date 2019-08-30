package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.fields.EntityID;
import fr.ubordeaux.ddd.annotations.methods.Getter;

import java.util.Objects;

import fr.ubordeaux.ddd.annotations.classes.Aggregate;
import fr.ubordeaux.ddd.annotations.classes.Entity;

@Aggregate
@Entity
public class AggregateEntityA {
    @EntityID
    private EntityB oneId;
    private ValueObjectB two;
    private ValueObjectC three;
    private int four;

    public AggregateEntityA(EntityB one) {
        this.oneId = one;
    }

    public void setTwo(ValueObjectB two) {
        this.two = two;
    }

    public void setThree(ValueObjectC three) {
        this.three = three;
    }

    public void setFour(int four) {
        this.four = four;
    }

    @Getter
    public EntityB getOne() {
        return this.oneId;
    }

    public ValueObjectB getTwo() {
        return this.two;
    }

    public ValueObjectC getThree() {
        return this.three;
    }

    public int getFour() {
        return this.four;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AggregateEntityA)) return false;
        return this.oneId.equals(((AggregateEntityA)other).oneId);
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
