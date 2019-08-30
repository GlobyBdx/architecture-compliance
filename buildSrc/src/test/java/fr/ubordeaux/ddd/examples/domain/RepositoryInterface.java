package fr.ubordeaux.ddd.examples.domain;

import java.util.List;

public interface RepositoryInterface {
    public void store(AggregateEntityA aggregate);
    public List<AggregateEntityA> findAll();
    public AggregateEntityA findByOne(EntityB one);
    public List<AggregateEntityA> findByTwo(ValueObjectB two);
    public List<AggregateEntityA> findByThree(ValueObjectC three);
    public List<AggregateEntityA> findByFour(int four);
}
