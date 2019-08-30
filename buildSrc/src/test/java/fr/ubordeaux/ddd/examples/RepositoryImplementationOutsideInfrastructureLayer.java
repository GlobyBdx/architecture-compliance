package fr.ubordeaux.ddd.examples;

import java.util.ArrayList;
import java.util.List;

import fr.ubordeaux.ddd.annotations.classes.Repository;
import fr.ubordeaux.ddd.examples.domain.AggregateEntityA;
import fr.ubordeaux.ddd.examples.domain.EntityB;
import fr.ubordeaux.ddd.examples.domain.ValueObjectB;
import fr.ubordeaux.ddd.examples.domain.ValueObjectC;

@Repository
public class RepositoryImplementationOutsideInfrastructureLayer implements RepositoryInterfaceOutsideDomainLayer {
    private List<AggregateEntityA> aggregates;

    public RepositoryImplementationOutsideInfrastructureLayer() {
        this.aggregates = new ArrayList<>();
    }

    @Override
    public void store(AggregateEntityA aggregate) {
        if (this.findAll().contains(aggregate)) {
            this.aggregates.remove(aggregate);
        }
        this.aggregates.add(aggregate);
    }

    @Override
    public List<AggregateEntityA> findAll() {
        return aggregates;
    }

    @Override
    public AggregateEntityA findByOne(EntityB one) {
        for (AggregateEntityA aggregate : this.findAll()) {
            if (aggregate.getOne().equals(one)) {
                return aggregate;
            }
        }
        return null;
    }

    @Override
    public List<AggregateEntityA> findByTwo(ValueObjectB two) {
        List<AggregateEntityA> aggregates = new ArrayList<>();
        for (AggregateEntityA aggregate : this.findAll()) {
            if (aggregate.getTwo().equals(two)) {
                aggregates.add(aggregate);
            }
        }
        return aggregates;
    }

    @Override
    public List<AggregateEntityA> findByThree(ValueObjectC three) {
        List<AggregateEntityA> aggregates = new ArrayList<>();
        for (AggregateEntityA aggregate : this.findAll()) {
            if (aggregate.getThree().equals(three)) {
                aggregates.add(aggregate);
            }
        }
        return aggregates;
    }

    @Override
    public List<AggregateEntityA> findByFour(int four) {
        List<AggregateEntityA> aggregates = new ArrayList<>();
        for (AggregateEntityA aggregate : this.findAll()) {
            if (aggregate.getFour() == four) {
                aggregates.add(aggregate);
            }
        }
        return aggregates;
    }
}
