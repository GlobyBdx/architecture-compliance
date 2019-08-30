package fr.ubordeaux.ddd.examples.domain;

import java.util.List;

public interface RepositoryInterfaceWithoutAccess {
    public void store(String value);
    public List<String> findAll();
    public String findByValue(String value);
}
