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
import fr.ubordeaux.ddd.examples.AggregateOutsideDomainLayer;
import fr.ubordeaux.ddd.examples.domain.AggregateEntityA;
import fr.ubordeaux.ddd.examples.domain.AggregateEntityB;
import fr.ubordeaux.ddd.examples.domain.AggregateWithoutField;
import fr.ubordeaux.ddd.examples.domain.AggregateWithoutFieldId;
import fr.ubordeaux.ddd.examples.domain.AggregateWithoutGoodObjectMethodsA;
import fr.ubordeaux.ddd.examples.domain.AggregateWithoutGoodObjectMethodsB;
import fr.ubordeaux.ddd.examples.domain.AggregateWithoutObjectMethod;
import fr.ubordeaux.ddd.examples.domain.TokenWithConstructorAccess;
import fr.ubordeaux.ddd.plugin.PluginConfiguration;
import fr.ubordeaux.ddd.plugin.project.Project;
import fr.ubordeaux.ddd.plugin.rules.AggregateRules;
import fr.ubordeaux.ddd.plugin.syntax.Syntax;
import fr.ubordeaux.ddd.tests.utils.TestUtils;

@RunWith(Parameterized.class)
public class AggregateTests {
    private static final Class<?>[] classes = {
            AggregateEntityA.class,
            AggregateEntityB.class,
            AggregateEntityFactoryRepositoryServiceValueObject.class,
            AggregateOutsideDomainLayer.class,
            AggregateWithoutField.class,
            AggregateWithoutFieldId.class,
            AggregateWithoutGoodObjectMethodsA.class,
            AggregateWithoutGoodObjectMethodsB.class,
            AggregateWithoutObjectMethod.class,
            TokenWithConstructorAccess.class
    };

    private final AggregateRules rules;

    public AggregateTests(Syntax syntax) {
        if (PluginConfiguration.getProject() == null) {
            Project project = new Project(null, null, null);
            project.setClasses(new ClassFileImporter().importPackages("fr.ubordeaux.ddd.examples"));
            PluginConfiguration.setProject(project);
        }
        PluginConfiguration.setSyntax(syntax);
        this.rules = new AggregateRules();
    }

    @Parameters
    public static Collection<Syntax> generateParameters() {
        return TestUtils.generateSyntaxes();
    }

    @Test
    public void shouldAlsoBeEntities() {
        Class<?>[] exceptions = {
                AggregateOutsideDomainLayer.class,
                AggregateWithoutField.class,
                AggregateWithoutFieldId.class,
                AggregateWithoutGoodObjectMethodsA.class,
                AggregateWithoutGoodObjectMethodsB.class,
                AggregateWithoutObjectMethod.class
        };
        TestUtils.assertRule(rules.aggregatesMustAlsoBeEntities, classes, exceptions);
    }

    @Test
    public void shouldResideInDomainLayer() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                AggregateOutsideDomainLayer.class
        };
        TestUtils.assertRule(rules.aggregatesMustResideInDomainLayer, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeFactories() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.aggregatesMustNotAlsoBeFactories, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeRepositories() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.aggregatesMustNotAlsoBeRepositories, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeServices() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.aggregatesMustNotAlsoBeServices, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeValueObjects() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.aggregatesMustNotAlsoBeValueObjects, classes, exceptions);
    }

    @Test
    public void shouldHaveAtLeastOneEntityId() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                AggregateWithoutField.class,
                AggregateWithoutFieldId.class
        };
        TestUtils.assertRule(rules.aggregatesMustHaveAtLeastOneEntityId, classes, exceptions);
    }

    @Test
    public void shouldOverrideEqualsMethodAccessingAllEntityIds() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                AggregateWithoutGoodObjectMethodsA.class,
                AggregateWithoutGoodObjectMethodsB.class,
                AggregateWithoutObjectMethod.class
        };
        TestUtils.assertRule(rules.aggregatesMustOverrideEqualsMethodAccessingAllEntityIds, classes, exceptions);
    }

    @Test
    public void shouldOverrideHashCodeMethodAccessingAllEntityIds() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                AggregateWithoutGoodObjectMethodsA.class,
                AggregateWithoutGoodObjectMethodsB.class,
                AggregateWithoutObjectMethod.class
        };
        TestUtils.assertRule(rules.aggregatesMustOverrideHashCodeMethodAccessingAllEntityIds, classes, exceptions);
    }

    @Test
    public void shouldOverrideToStringMethodAccessingAllEntityIds() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                AggregateWithoutGoodObjectMethodsA.class,
                AggregateWithoutGoodObjectMethodsB.class,
                AggregateWithoutObjectMethod.class
        };
        TestUtils.assertRule(rules.aggregatesMustOverrideToStringMethodAccessingAllEntityIds, classes, exceptions);
    }

    @Test
    public void shouldOnlyBeExternallyAccessedFromOwnerAggregate() {
        Class<?>[] exceptions = {
                AggregateEntityB.class,
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                AggregateOutsideDomainLayer.class,
                AggregateWithoutField.class,
                AggregateWithoutFieldId.class,
                AggregateWithoutGoodObjectMethodsA.class,
                AggregateWithoutGoodObjectMethodsB.class,
                AggregateWithoutObjectMethod.class,
                TokenWithConstructorAccess.class
        };
        TestUtils.assertRule(rules.aggregatesInternalObjectsMustOnlyBeExternallyAccessedFromOwnerAggregate, classes, exceptions);
    }

    @Test
    public void shouldOnlyBeExternallyInstantiatedByOwnerAggregateFactory() {
        Class<?>[] exceptions = {
                AggregateEntityA.class,
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                TokenWithConstructorAccess.class
        };
        TestUtils.assertRule(rules.aggregatesInternalObjectsMustOnlyBeExternallyInstantiatedByOwnerAggregateFactory, classes, exceptions);
    }
}
