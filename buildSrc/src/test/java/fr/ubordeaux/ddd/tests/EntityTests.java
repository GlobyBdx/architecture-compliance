/*
 * @author Benoit Faget
 */

package fr.ubordeaux.ddd.tests;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.tngtech.archunit.core.importer.ClassFileImporter;

import fr.ubordeaux.ddd.examples.AggregateEntityFactoryRepositoryServiceValueObject;
import fr.ubordeaux.ddd.examples.EntityOutsideDomainLayer;
import fr.ubordeaux.ddd.examples.domain.EntityA;
import fr.ubordeaux.ddd.examples.domain.EntityB;
import fr.ubordeaux.ddd.examples.domain.EntityC;
import fr.ubordeaux.ddd.examples.domain.EntityD;
import fr.ubordeaux.ddd.examples.domain.EntityWithAccessIssue;
import fr.ubordeaux.ddd.examples.domain.EntityWithGetterIssue;
import fr.ubordeaux.ddd.examples.domain.EntityWithNonPrivateFieldId;
import fr.ubordeaux.ddd.examples.domain.EntityWithNonPrivateSetter;
import fr.ubordeaux.ddd.examples.domain.EntityWithoutField;
import fr.ubordeaux.ddd.examples.domain.EntityWithoutFieldId;
import fr.ubordeaux.ddd.examples.domain.EntityWithoutGoodObjectMethodsA;
import fr.ubordeaux.ddd.examples.domain.EntityWithoutGoodObjectMethodsB;
import fr.ubordeaux.ddd.examples.domain.EntityWithoutObjectMethod;
import fr.ubordeaux.ddd.examples.domain.TokenWithFieldId;
import fr.ubordeaux.ddd.plugin.PluginConfiguration;
import fr.ubordeaux.ddd.plugin.project.Project;
import fr.ubordeaux.ddd.plugin.rules.EntityRules;
import fr.ubordeaux.ddd.plugin.syntax.Syntax;
import fr.ubordeaux.ddd.tests.utils.TestUtils;

@RunWith(Parameterized.class)
public class EntityTests {
    private static final Class<?>[] classes = {
            AggregateEntityFactoryRepositoryServiceValueObject.class,
            EntityA.class,
            EntityB.class,
            EntityC.class,
            EntityD.class,
            EntityOutsideDomainLayer.class,
            EntityWithAccessIssue.class,
            EntityWithGetterIssue.class,
            EntityWithNonPrivateFieldId.class,
            EntityWithNonPrivateSetter.class,
            EntityWithoutField.class,
            EntityWithoutFieldId.class,
            EntityWithoutGoodObjectMethodsA.class,
            EntityWithoutGoodObjectMethodsB.class,
            EntityWithoutObjectMethod.class,
            TokenWithFieldId.class
    };

    private final EntityRules rules;

    public EntityTests(Syntax syntax) {
        if (PluginConfiguration.getProject() == null) {
            Project project = new Project(null, null, null);
            project.setClasses(new ClassFileImporter().importPackages("fr.ubordeaux.ddd.examples"));
            PluginConfiguration.setProject(project);
        }
        PluginConfiguration.setSyntax(syntax);
        this.rules = new EntityRules();
    }

    @Parameters
    public static Collection<Syntax> generateParameters() {
        return TestUtils.generateSyntaxes();
    }

    @Test
    public void shouldResideInDomainLayer() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                EntityOutsideDomainLayer.class
        };
        TestUtils.assertRule(rules.entitiesMustResideInDomainLayer, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeFactories() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.entitiesMustNotAlsoBeFactories, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeRepositories() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.entitiesMustNotAlsoBeRepositories, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeServices() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.entitiesMustNotAlsoBeServices, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeValueObjects() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.entitiesMustNotAlsoBeValueObjects, classes, exceptions);
    }

    @Test
    public void shouldHaveAtLeastOneEntityId() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                EntityWithoutField.class,
                EntityWithoutFieldId.class
        };
        TestUtils.assertRule(rules.entitiesMustHaveAtLeastOneEntityId, classes, exceptions);
    }

    @Test
    public void shouldOverrideEqualsMethodAccessingAllEntityIds() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                EntityWithoutGoodObjectMethodsA.class,
                EntityWithoutGoodObjectMethodsB.class,
                EntityWithoutObjectMethod.class
        };
        TestUtils.assertRule(rules.entitiesMustOverrideEqualsMethodAccessingAllEntityIds, classes, exceptions);
    }

    @Test
    public void shouldOverrideHashCodeMethodAccessingAllEntityIds() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                EntityWithoutGoodObjectMethodsA.class,
                EntityWithoutGoodObjectMethodsB.class,
                EntityWithoutObjectMethod.class
        };
        TestUtils.assertRule(rules.entitiesMustOverrideHashCodeMethodAccessingAllEntityIds, classes, exceptions);
    }

    @Test
    public void shouldOverrideToStringMethodAccessingAllEntityIds() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                EntityWithoutGoodObjectMethodsA.class,
                EntityWithoutGoodObjectMethodsB.class,
                EntityWithoutObjectMethod.class
        };
        TestUtils.assertRule(rules.entitiesMustOverrideToStringMethodAccessingAllEntityIds, classes, exceptions);
    }

    @Test
    public void shouldOnlyBeDeclaredInEntities() {
        Class<?>[] exceptions = {
                TokenWithFieldId.class
        };
        TestUtils.assertRule(rules.entityIdsMustOnlyBeDeclaredInEntities, classes, exceptions);
    }

    @Test
    public void shouldOnlyBeDeclaredInAggregatesEntities() {
        Class<?>[] exceptions = {
                TokenWithFieldId.class
        };
        TestUtils.assertRule(rules.entityIdsMustOnlyBeDeclaredInAggregatesEntities, classes, exceptions);
    }

    @Test
    public void shouldOnlyBePrivate() {
        Class<?>[] exceptions = {
                EntityWithNonPrivateFieldId.class
        };
        TestUtils.assertRule(rules.entityIdsMustBePrivate, classes, exceptions);
    }

    @Test
    public void shouldOnlyBeFinal() {
        Class<?>[] exceptions = {
                EntityB.class,
                EntityC.class,
                EntityD.class,
                EntityOutsideDomainLayer.class,
                EntityWithAccessIssue.class,
                EntityWithGetterIssue.class,
                EntityWithNonPrivateFieldId.class,
                EntityWithNonPrivateSetter.class,
                EntityWithoutField.class,
                EntityWithoutFieldId.class,
                EntityWithoutGoodObjectMethodsA.class,
                EntityWithoutGoodObjectMethodsB.class,
                EntityWithoutObjectMethod.class,
                TokenWithFieldId.class
        };
        TestUtils.assertRule(rules.entityIdsMustBeFinal, classes, exceptions);
    }

    @Test
    public void shouldOnlyBeImmutables() {
        Class<?>[] exceptions = {
                EntityB.class,
                EntityOutsideDomainLayer.class,
                EntityWithAccessIssue.class,
                EntityWithGetterIssue.class,
                EntityWithNonPrivateFieldId.class,
                EntityWithNonPrivateSetter.class,
                EntityWithoutField.class,
                EntityWithoutFieldId.class,
                EntityWithoutGoodObjectMethodsA.class,
                EntityWithoutGoodObjectMethodsB.class,
                EntityWithoutObjectMethod.class,
                TokenWithFieldId.class
        };
        TestUtils.assertRule(rules.entityIdsMustBeImmutables, classes, exceptions);
    }

    @Test
    public void shouldHavePrivateSetters() {
        Class<?>[] exceptions = {
                EntityWithNonPrivateSetter.class
        };
        TestUtils.assertRule(rules.entityIdsMustHavePrivateSetters, classes, exceptions);
    }

    @Test
    public void shouldHaveSpecificGetters() {
        Class<?>[] exceptions = {
                EntityWithGetterIssue.class
        };
        TestUtils.assertRule(rules.entityIdsMustHaveSpecificGetters, classes, exceptions);
    }

    @Test
    public void shouldHaveSpecificAccesses() {
        Class<?>[] exceptions = {
                EntityWithAccessIssue.class,
                EntityWithoutGoodObjectMethodsB.class
        };
        TestUtils.assertRule(rules.entityIdsMustHaveSpecificAccesses, classes, exceptions);
    }
}
