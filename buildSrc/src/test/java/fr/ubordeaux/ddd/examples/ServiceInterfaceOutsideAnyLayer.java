package fr.ubordeaux.ddd.examples;

import fr.ubordeaux.ddd.examples.domain.AggregateEntityA;

public interface ServiceInterfaceOutsideAnyLayer {
    public void addAggregate(String one);
    public void addAggregate(String one, AggregateEntityA prototype);
    public void addAggregate(AggregateEntityA aggregate);
}
