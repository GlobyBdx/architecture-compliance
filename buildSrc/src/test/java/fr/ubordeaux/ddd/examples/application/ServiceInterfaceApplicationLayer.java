package fr.ubordeaux.ddd.examples.application;

import fr.ubordeaux.ddd.examples.domain.AggregateEntityA;

public interface ServiceInterfaceApplicationLayer {
    public void addAggregate(String one);
    public void addAggregate(String one, AggregateEntityA prototype);
    public void addAggregate(AggregateEntityA aggregate);
}
