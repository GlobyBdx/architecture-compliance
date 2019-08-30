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
import fr.ubordeaux.ddd.examples.RepositoryImplementationOutsideInfrastructureLayer;
import fr.ubordeaux.ddd.examples.infrastructure.RepositoryImplementation;
import fr.ubordeaux.ddd.examples.infrastructure.RepositoryImplementationWithoutAccess;
import fr.ubordeaux.ddd.examples.infrastructure.RepositoryWithoutInterface;
import fr.ubordeaux.ddd.plugin.PluginConfiguration;
import fr.ubordeaux.ddd.plugin.project.Project;
import fr.ubordeaux.ddd.plugin.rules.RepositoryRules;
import fr.ubordeaux.ddd.plugin.syntax.Syntax;
import fr.ubordeaux.ddd.tests.utils.TestUtils;

@RunWith(Parameterized.class)
public class RepositoryTests {
    private static final Class<?>[] classes = {
            AggregateEntityFactoryRepositoryServiceValueObject.class,
            RepositoryImplementation.class,
            RepositoryImplementationOutsideInfrastructureLayer.class,
            RepositoryImplementationWithoutAccess.class,
            RepositoryWithoutInterface.class
    };

    private final RepositoryRules rules;

    public RepositoryTests(Syntax syntax) {
        if (PluginConfiguration.getProject() == null) {
            Project project = new Project(null, null, null);
            project.setClasses(new ClassFileImporter().importPackages("fr.ubordeaux.ddd.examples"));
            PluginConfiguration.setProject(project);
        }
        PluginConfiguration.setSyntax(syntax);
        this.rules = new RepositoryRules();
    }

    @Parameters
    public static Collection<Syntax> generateParameters() {
        return TestUtils.generateSyntaxes();
    }

    @Test
    public void shouldResideInInfrastructureLayer() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                RepositoryImplementationOutsideInfrastructureLayer.class
        };
        TestUtils.assertRule(rules.repositoriesMustResideInInfrastructureLayer, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeAggregates() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.repositoriesMustNotAlsoBeAggregates, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeEntities() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.repositoriesMustNotAlsoBeEntities, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeFactories() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.repositoriesMustNotAlsoBeFactories, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeServices() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.repositoriesMustNotAlsoBeServices, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeValueObjects() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.repositoriesMustNotAlsoBeValueObjects, classes, exceptions);
    }

    @Test
    public void shouldImplementInterfaceInDomainLayer() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                RepositoryImplementationOutsideInfrastructureLayer.class,
                RepositoryWithoutInterface.class
        };
        TestUtils.assertRule(rules.repositoriesMustImplementInterfaceInDomainLayer, classes, exceptions);
    }

    @Test
    public void shouldAccessAtLeastOneAggregateEntityValueObject() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                RepositoryImplementationWithoutAccess.class,
                RepositoryWithoutInterface.class
        };
        TestUtils.assertRule(rules.repositoriesMustAccessAtLeastOneAggregateEntityValueObject, classes, exceptions);
    }
}
