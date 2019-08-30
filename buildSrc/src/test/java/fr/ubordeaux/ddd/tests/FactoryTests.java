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
import fr.ubordeaux.ddd.examples.FactoryOutsideDomainLayer;
import fr.ubordeaux.ddd.examples.domain.FactoryA;
import fr.ubordeaux.ddd.examples.domain.FactoryB;
import fr.ubordeaux.ddd.examples.domain.FactoryWithPrivateMethods;
import fr.ubordeaux.ddd.examples.domain.FactoryWithoutConstructorAccess;
import fr.ubordeaux.ddd.examples.domain.FactoryWithoutMethod;
import fr.ubordeaux.ddd.examples.domain.FactoryWithoutSameReturnType;
import fr.ubordeaux.ddd.plugin.PluginConfiguration;
import fr.ubordeaux.ddd.plugin.project.Project;
import fr.ubordeaux.ddd.plugin.rules.FactoryRules;
import fr.ubordeaux.ddd.plugin.syntax.Syntax;
import fr.ubordeaux.ddd.tests.utils.TestUtils;

@RunWith(Parameterized.class)
public class FactoryTests {
    private static final Class<?>[] classes = {
            AggregateEntityFactoryRepositoryServiceValueObject.class,
            FactoryA.class,
            FactoryB.class,
            FactoryOutsideDomainLayer.class,
            FactoryWithoutConstructorAccess.class,
            FactoryWithoutMethod.class,
            FactoryWithoutSameReturnType.class,
            FactoryWithPrivateMethods.class
    };

    private final FactoryRules rules;

    public FactoryTests(Syntax syntax) {
        if (PluginConfiguration.getProject() == null) {
            Project project = new Project(null, null, null);
            project.setClasses(new ClassFileImporter().importPackages("fr.ubordeaux.ddd.examples"));
            PluginConfiguration.setProject(project);
        }
        PluginConfiguration.setSyntax(syntax);
        this.rules = new FactoryRules();
    }

    @Parameters
    public static Collection<Syntax> generateParameters() {
        return TestUtils.generateSyntaxes();
    }

    @Test
    public void shouldResideInDomainLayer() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                FactoryOutsideDomainLayer.class
        };
        TestUtils.assertRule(rules.factoriesMustResideInDomainLayer, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeAggregates() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.factoriesMustNotAlsoBeAggregates, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeEntities() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.factoriesMustNotAlsoBeEntities, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeRepositories() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.factoriesMustNotAlsoBeRepositories, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeServices() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.factoriesMustNotAlsoBeServices, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeValueObjects() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.factoriesMustNotAlsoBeValueObjects, classes, exceptions);
    }

    @Test
    public void shouldHaveNonPrivateMethodsWithSameReturnType() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                FactoryWithoutMethod.class,
                FactoryWithoutSameReturnType.class,
                FactoryWithPrivateMethods.class
        };
        TestUtils.assertRule(rules.factoriesMustHaveNonPrivateMethodsWithSameReturnType, classes, exceptions);
    }

    @Test
    public void shouldAccessAtLeastOneConstructorFromAggregateEntityValueObject() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                FactoryWithoutConstructorAccess.class,
                FactoryWithoutMethod.class
        };
        TestUtils.assertRule(rules.factoriesMustAccessAtLeastOneConstructorFromAggregateEntityValueObject, classes, exceptions);
    }
}
