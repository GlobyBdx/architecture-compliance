package fr.ubordeaux.ddd.examples.domain;

import fr.ubordeaux.ddd.annotations.classes.Factory;

@Factory
public class FactoryA {
    public AggregateEntityA newAggregateA(String one) {
        return new AggregateEntityA(new EntityB(one));
    }

    public AggregateEntityA newAggregateA(String one, AggregateEntityA prototype) {
        AggregateEntityA aggregate = newAggregateA(one);
        aggregate.setTwo(prototype.getTwo());
        aggregate.setThree(prototype.getThree());
        aggregate.setFour(prototype.getFour());
        return aggregate;
    }
}
