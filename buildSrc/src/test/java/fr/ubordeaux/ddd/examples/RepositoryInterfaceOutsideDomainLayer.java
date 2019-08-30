package fr.ubordeaux.ddd.examples;

import java.util.List;

import fr.ubordeaux.ddd.examples.domain.AggregateEntityA;
import fr.ubordeaux.ddd.examples.domain.EntityB;
import fr.ubordeaux.ddd.examples.domain.ValueObjectB;
import fr.ubordeaux.ddd.examples.domain.ValueObjectC;

public interface RepositoryInterfaceOutsideDomainLayer {
    public void store(AggregateEntityA aggregate);
    public List<AggregateEntityA> findAll();
    public AggregateEntityA findByOne(EntityB one);
    public List<AggregateEntityA> findByTwo(ValueObjectB two);
    public List<AggregateEntityA> findByThree(ValueObjectC three);
    public List<AggregateEntityA> findByFour(int four);
}
