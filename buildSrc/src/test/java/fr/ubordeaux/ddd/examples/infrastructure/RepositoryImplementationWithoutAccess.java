package fr.ubordeaux.ddd.examples.infrastructure;

import java.util.ArrayList;
import java.util.List;

import fr.ubordeaux.ddd.annotations.classes.Repository;
import fr.ubordeaux.ddd.examples.domain.RepositoryInterfaceWithoutAccess;

@Repository
public class RepositoryImplementationWithoutAccess implements RepositoryInterfaceWithoutAccess {
    private List<String> values;

    public RepositoryImplementationWithoutAccess() {
        this.values = new ArrayList<>();
    }

    @Override
    public void store(String value) {
        if (this.findAll().contains(value)) {
            this.values.remove(value);
        }
        this.values.add(value);
    }

    @Override
    public List<String> findAll() {
        return values;
    }

    @Override
    public String findByValue(String value) {
        for (String storedValue : this.findAll()) {
            if (storedValue.compareTo(value) == 0) {
                return storedValue;
            }
        }
        return null;
    }
}
