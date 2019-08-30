package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.classes.Factory;

@Factory
public class FactoryWithoutConstructorAccess {
    public AggregateEntityB newAggregateB(String one) {
        return null;
    }

    public AggregateEntityB newAggregateB(String one, AggregateEntityB prototype) {
        return null;
    }
}
