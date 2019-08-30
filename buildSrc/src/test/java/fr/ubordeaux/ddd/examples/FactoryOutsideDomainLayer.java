package fr.ubordeaux.ddd.examples;

import fr.ubordeaux.ddd.annotations.classes.Factory;
import fr.ubordeaux.ddd.examples.domain.AggregateEntityB;

@Factory
public class FactoryOutsideDomainLayer {
    public AggregateEntityB newAggregateB(String one) {
        return new AggregateEntityB(one);
    }

    public AggregateEntityB newAggregateB(String one, AggregateEntityB prototype) {
        AggregateEntityB aggregate = newAggregateB(one);
        aggregate.setTwo(prototype.getTwo());
        aggregate.setThree(prototype.getThree());
        aggregate.setFour(prototype.getFour());
        return aggregate;
    }
}
