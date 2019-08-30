# Available Rules

## Domain-Driven Design

### Layers

| `anticorruptionLayerClassesMustNotResideInAnotherLayer` |
| ------------------------------------------------------- |
| Classes which reside in a package or a sub-package following the anticorruption layer syntax should not also reside in a package or a sub-package following another layer syntax. |

| `applicationLayerClassesMustNotResideInAnotherLayer` |
| ---------------------------------------------------- |
| Classes which reside in a package or a sub-package following the application layer syntax should not also reside in a package or a sub-package following another layer syntax. |

| `domainLayerClassesMustNotResideInAnotherLayer` |
| ----------------------------------------------- |
| Classes which reside in a package or a sub-package following the domain layer syntax should not also reside in a package or a sub-package following another layer syntax. |

| `infrastructureLayerClassesMustNotResideInAnotherLayer` |
| ------------------------------------------------------- |
| Classes which reside in a package or a sub-package following the infrastructure layer syntax should not also reside in a package or a sub-package following another layer syntax. |

| `presentationLayerClassesMustNotResideInAnotherLayer` |
| ----------------------------------------------------- |
| Classes which reside in a package or a sub-package following the presentation layer syntax should not also reside in a package or a sub-package following another layer syntax. |

| `anticorruptionLayerDependenciesMustBeRespected` |
| ------------------------------------------------ |
| Classes which reside in a package or a sub-package following the anticorruption layer syntax should not be accessed by classes which reside in a package or a sub-package following another layer syntax. |

| `applicationLayerDependenciesMustBeRespected` |
| --------------------------------------------- |
| Classes which reside in a package or a sub-package following the application layer syntax should only be accessed by classes which reside in a package or a sub-package following the anticorruption or presentation layer syntax. |

| `infrastructureLayerDependenciesMustBeRespected` |
| ------------------------------------------------ |
| Classes which reside in a package or a sub-package following the infrastructure layer syntax should only be accessed by classes which reside in a package or a sub-package following the anticorruption, application or presentation layer syntax. |

| `presentationLayerDependenciesMustBeRespected` |
| ---------------------------------------------- |
| Classes which reside in a package or a sub-package following the presentation layer syntax should not be accessed by classes which reside in a package or a sub-package following another layer syntax. |

### Aggregate

| `aggregatesMustAlsoBeEntities` |
| ------------------------------ |
| Classes following the aggregate syntax should also be following the entity syntax. Ensures that rules on entities will also be applied on aggregates. |

| `aggregatesMustResideInDomainLayer` |
| ----------------------------------- |
| Classes following the aggregate syntax should reside in a package or a sub-package following the domain layer syntax. |

| `aggregatesMustNotAlsoBeFactories` |
| ---------------------------------- |
| Classes following the aggregate syntax should not also be following the factory syntax. |

| `aggregatesMustNotAlsoBeRepositories` |
| ------------------------------------- |
| Classes following the aggregate syntax should not also be following the repository syntax. |

| `aggregatesMustNotAlsoBeServices` |
| --------------------------------- |
| Classes following the aggregate syntax should not also be following the service syntax. |

| `aggregatesMustNotAlsoBeValueObjects` |
| ------------------------------------- |
| Classes following the aggregate syntax should not also be following the value object syntax. |

| `aggregatesMustHaveAtLeastOneEntityId` |
| -------------------------------------- |
| Classes following the aggregate syntax should have at least one field following the entity ID syntax. |

| `aggregatesMustOverrideEqualsMethodAccessingAllEntityIds` |
| --------------------------------------------------------- |
| Classes following the aggregate syntax should override the java.lang.Object equals method accessing all their fields following the entity ID syntax. |

| `aggregatesMustOverrideHashCodeMethodAccessingAllEntityIds` |
| ----------------------------------------------------------- |
| Classes following the aggregate syntax should override the java.lang.Object hashCode method accessing all their fields following the entity ID syntax. |

| `aggregatesMustOverrideToStringMethodAccessingAllEntityIds` |
| ----------------------------------------------------------- |
| Classes following the aggregate syntax should override the java.lang.Object toString method accessing all their fields following the entity ID syntax. |

| `aggregatesInternalObjectsMustOnlyBeExternallyAccessedFromOwnerAggregate` |
| ------------------------------------------------------------------------- |
| Internal objects of classes following the aggregate syntax should only be externally accessed from their owner class. |

| `aggregatesInternalObjectsMustOnlyBeExternallyInstantiatedByOwnerAggregateFactory` |
| ---------------------------------------------------------------------------------- |
| Internal objects of classes following the aggregate syntax should only be externally instantiated by classes following the factory syntax and providing factory services to their owner class. |

### Entity

| `entitiesMustResideInDomainLayer` |
| --------------------------------- |
| Classes following the entity syntax should reside in a package or a sub-package following the domain layer syntax. |

| `entitiesMustNotAlsoBeFactories` |
| -------------------------------- |
| Classes following the entity syntax should not also be following the factory syntax. |

| `entitiesMustNotAlsoBeRepositories` |
| ----------------------------------- |
| Classes following the entity syntax should not also be following the repository syntax. |

| `entitiesMustNotAlsoBeServices` |
| ------------------------------- |
| Classes following the entity syntax should not also be following the service syntax. |

| `entitiesMustNotAlsoBeValueObjects` |
| ----------------------------------- |
| Classes following the entity syntax should not also be following the value object syntax. |

| `entitiesMustHaveAtLeastOneEntityId` |
| ------------------------------------ |
| Classes following the entity syntax should have at least one field following the entity ID syntax. |

| `entitiesMustOverrideEqualsMethodAccessingAllEntityIds` |
| ------------------------------------------------------- |
| Classes following the entity syntax should override equals method accesing all fields following the entity ID syntax. |

| `entitiesMustOverrideHashCodeMethodAccessingAllEntityIds` |
| --------------------------------------------------------- |
| Classes following the entity syntax should override hashCode method accesing all fields following the entity ID syntax. |

| `entitiesMustOverrideToStringMethodAccessingAllEntityIds` |
| --------------------------------------------------------- |
| Classes following the entity syntax should override toString method accesing all fields following the entity ID syntax. |

| `entityIdsMustOnlyBeDeclaredInEntities` |
| --------------------------------------- |
| Fields following the entity ID syntax should only be declared in classes following the entity syntax. |

| `entityIdsMustOnlyBeDeclaredInAggregatesEntities` |
| ------------------------------------------------- |
| Fields following the entity ID syntax should only be declared in classes following the aggregate or entity syntax. |

| `entityIdsMustBePrivate` |
| ------------------------ |
| Fields following the entity ID syntax should be private. |

| `entityIdsMustBeFinal` |
| ---------------------- |
| Fields following the entity ID syntax should be final. |

| `entityIdsMustBeImmutables` |
| --------------------------- |
| Fields following the entity ID syntax should also be following the immutable syntax. |

| `entityIdsMustHavePrivateSetters` |
| --------------------------------- |
| Fields following the entity ID syntax should have private setters. |

| `entityIdsMustHaveSpecificGetters` |
| ---------------------------------- |
| Fields following the entity ID syntax should have specific getters. |

| `entityIdsMustHaveSpecificAccesses` |
| ----------------------------------- |
| Fields following the entity ID syntax should have specific accesses. |

### Factory

| `factoriesMustResideInDomainLayer` |
| ---------------------------------- |
| Classes following the factory syntax should reside in a package or a sub-package following the domain layer syntax. |

| `factoriesMustNotAlsoBeAggregates` |
| ---------------------------------- |
| Classes following the factory syntax should not also be following the aggregate syntax. |

| `factoriesMustNotAlsoBeEntities` |
| -------------------------------- |
| Classes following the factory syntax should not also be following the entity syntax. |

| `factoriesMustNotAlsoBeRepositories` |
| ------------------------------------ |
| Classes following the factory syntax should not also be following the repository syntax. |

| `factoriesMustNotAlsoBeServices` |
| -------------------------------- |
| Classes following the factory syntax should not also be following the service syntax. |

| `factoriesMustNotAlsoBeValueObjects` |
| ------------------------------------ |
| Classes following the factory syntax should not also be following the value object syntax. |

| `factoriesMustHaveNonPrivateMethodsWithSameReturnType` |
| ------------------------------------------------------ |
| Classes following the factory syntax should have non-private methods with the same return type. |

| `factoriesMustAccessAtLeastOneConstructorFromAggregateEntityValueObject` |
| ------------------------------------------------------------------------ |
| Classes following the factory syntax should access at least one constructor from a class following the aggregate, entity or value object syntax. |

### Repository

| `repositoriesMustResideInInfrastructureLayer` |
| --------------------------------------------- |
| Classes following the repository syntax should reside in a package or a sub-package following the infrastructure layer syntax. |

| `repositoriesMustNotAlsoBeAggregates` |
| ------------------------------------- |
| Classes following the repository syntax should not also be following the aggregate syntax. |

| `repositoriesMustNotAlsoBeEntities` |
| ----------------------------------- |
| Classes following the repository syntax should not also be following the entity syntax. |

| `repositoriesMustNotAlsoBeFactories` |
| ------------------------------------ |
| Classes following the repository syntax should not also be following the factory syntax. |

| `repositoriesMustNotAlsoBeServices` |
| ----------------------------------- |
| Classes following the repository syntax should not also be following the service syntax. |

| `repositoriesMustNotAlsoBeValueObjects` |
| --------------------------------------- |
| Classes following the repository syntax should not also be following the value object syntax. |

| `repositoriesMustImplementInterfaceInDomainLayer` |
| ------------------------------------------------- |
| Classes following the repository syntax should implement an interface which reside in a package or a sub-package following the domain layer syntax. |

| `repositoriesMustAccessAtLeastOneAggregateEntityValueObject` |
| ------------------------------------------------------------ |
| Classes following the repository syntax should access at least one class following the aggregate, entity or value object syntax. |

### Service

| `servicesMustResideInApplicationDomainInfrastructureLayer` |
| ---------------------------------------------------------- |
| Classes following the service syntax should reside in a package or a sub-package following the application, domain or infrastructure layer syntax. |

| `servicesMustNotAlsoBeAggregates` |
| --------------------------------- |
| Classes following the service syntax should not also be following the aggregate syntax. |

| `servicesMustNotAlsoBeEntities` |
| ------------------------------- |
| Classes following the service syntax should not also be following the entity syntax. |

| `servicesMustNotAlsoBeFactories` |
| -------------------------------- |
| Classes following the service syntax should not also be following the factory syntax. |

| `servicesMustNotAlsoBeRepositories` |
| ----------------------------------- |
| Classes following the service syntax should not also be following the repository syntax. |

| `servicesMustNotAlsoBeValueObjects` |
| ----------------------------------- |
| Classes following the service syntax should not also be following the value object syntax. |

| `servicesFieldsMustBeFinal` |
| --------------------------- |
| Fields of classes following the service syntax should be final. |

| `servicesMustImplementInterfaceInSameLayer` |
| ------------------------------------------- |
| Classes following the service syntax should implement an interface which reside in a package or a sub-package following the same layer syntax. |

### Value Object

| `valueObjectsMustResideInDomainLayer` |
| ------------------------------------- |
| Classes following the value object syntax should reside in a package or a sub-package following the domain layer syntax. |

| `valueObjectsMustNotAlsoBeAggregates` |
| ------------------------------------- |
| Classes following the value object syntax should not also be following the aggregate syntax. |

| `valueObjectsMustNotAlsoBeEntities` |
| ----------------------------------- |
| Classes following the value object syntax should not also be following the entity syntax. |

| `valueObjectsMustNotAlsoBeFactories` |
| ------------------------------------ |
| Classes following the value object syntax should not also be following the factory syntax. |

| `valueObjectsMustNotAlsoBeRepositories` |
| --------------------------------------- |
| Classes following the value object syntax should not also be following the repository syntax. |

| `valueObjectsMustNotAlsoBeServices` |
| ----------------------------------- |
| Classes following the value object syntax should not also be following the service syntax. |

| `valueObjectsMustHaveAtLeastOneField` |
| ------------------------------------- |
| Classes following the value object syntax should have at least one field. |

| `valueObjectsMustOverrideEqualsMethodAccessingAllFields` |
| -------------------------------------------------------- |
| Classes following the value object syntax should override equals method accesing all fields. |

| `valueObjectsMustOverrideHashCodeMethodAccessingAllFields` |
| ---------------------------------------------------------- |
| Classes following the value object syntax should override hashCode method accesing all fields. |

| `valueObjectsMustOverrideToStringMethodAccessingAllFields` |
| ---------------------------------------------------------- |
| Classes following the value object syntax should override toString method accesing all fields. |

| `valueObjectsFieldsMustBePrivate` |
| --------------------------------- |
| Fields declared in classes following the value object syntax should be private. |

| `valueObjectsFieldsMustBeFinal` |
| ------------------------------- |
| Fields declared in classes following the value object syntax should be final. |

| `valueObjectsFieldsMustBeImmutables` |
| ------------------------------------ |
| Fields declared in classes following the value object syntax should be following the immutable syntax. |

| `valueObjectsFieldsMustHavePrivateSetters` |
| ------------------------------------------ |
| Fields declared in classes following the value object syntax should have private setters. |

| `valueObjectsFieldsMustHaveSpecificGetters` |
| ------------------------------------------- |
| Fields declared in classes following the value object syntax should have specific getters. |

| `valueObjectsFieldsMustHaveSpecificAccesses` |
| -------------------------------------------- |
| Fields declared in classes following the value object syntax should have specific accesses. |
