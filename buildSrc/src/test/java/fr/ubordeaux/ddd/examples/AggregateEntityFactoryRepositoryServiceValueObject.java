package fr.ubordeaux.ddd.examples;

import fr.ubordeaux.ddd.annotations.classes.Aggregate;
import fr.ubordeaux.ddd.annotations.classes.Entity;
import fr.ubordeaux.ddd.annotations.classes.Factory;
import fr.ubordeaux.ddd.annotations.classes.Repository;
import fr.ubordeaux.ddd.annotations.classes.Service;
import fr.ubordeaux.ddd.annotations.classes.ValueObject;

@Aggregate
@Entity
@Factory
@Repository
@Service
@ValueObject
public class AggregateEntityFactoryRepositoryServiceValueObject {}
