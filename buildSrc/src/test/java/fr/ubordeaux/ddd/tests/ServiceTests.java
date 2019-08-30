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
import fr.ubordeaux.ddd.examples.ServiceImplementationOutsideApplicationDomainInfrastructureLayer;
import fr.ubordeaux.ddd.examples.application.ServiceImplementationApplicationLayer;
import fr.ubordeaux.ddd.examples.application.ServiceImplementationWithNonFinalFields;
import fr.ubordeaux.ddd.examples.application.ServiceImplementationWithoutInterface;
import fr.ubordeaux.ddd.examples.domain.ServiceImplementationDomainLayer;
import fr.ubordeaux.ddd.examples.infrastructure.ServiceImplementationInfrastructureLayer;
import fr.ubordeaux.ddd.plugin.PluginConfiguration;
import fr.ubordeaux.ddd.plugin.project.Project;
import fr.ubordeaux.ddd.plugin.rules.ServiceRules;
import fr.ubordeaux.ddd.plugin.syntax.Syntax;
import fr.ubordeaux.ddd.tests.utils.TestUtils;

@RunWith(Parameterized.class)
public class ServiceTests {
    private static final Class<?>[] classes = {
            AggregateEntityFactoryRepositoryServiceValueObject.class,
            ServiceImplementationApplicationLayer.class,
            ServiceImplementationDomainLayer.class,
            ServiceImplementationInfrastructureLayer.class,
            ServiceImplementationOutsideApplicationDomainInfrastructureLayer.class,
            ServiceImplementationWithNonFinalFields.class,
            ServiceImplementationWithoutInterface.class
    };

    private final ServiceRules rules;

    public ServiceTests(Syntax syntax) {
        if (PluginConfiguration.getProject() == null) {
            Project project = new Project(null, null, null);
            project.setClasses(new ClassFileImporter().importPackages("fr.ubordeaux.ddd.examples"));
            PluginConfiguration.setProject(project);
        }
        PluginConfiguration.setSyntax(syntax);
        this.rules = new ServiceRules();
    }

    @Parameters
    public static Collection<Syntax> generateParameters() {
        return TestUtils.generateSyntaxes();
    }

    @Test
    public void shouldResideInApplicationDomainInfrastructureLayer() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                ServiceImplementationOutsideApplicationDomainInfrastructureLayer.class
        };
        TestUtils.assertRule(rules.servicesMustResideInApplicationDomainInfrastructureLayer, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeAggregates() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.servicesMustNotAlsoBeAggregates, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeEntities() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.servicesMustNotAlsoBeEntities, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeFactories() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.servicesMustNotAlsoBeFactories, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeRepositories() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.servicesMustNotAlsoBeRepositories, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeValueObjects() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.servicesMustNotAlsoBeValueObjects, classes, exceptions);
    }

    @Test
    public void shouldBeFinal() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                ServiceImplementationWithNonFinalFields.class,
        };
        TestUtils.assertRule(rules.servicesFieldsMustBeFinal, classes, exceptions);
    }

    @Test
    public void shouldImplementInterfaceInSameLayer() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                ServiceImplementationOutsideApplicationDomainInfrastructureLayer.class,
                ServiceImplementationWithoutInterface.class
        };
        TestUtils.assertRule(rules.servicesMustImplementInterfaceInSameLayer, classes, exceptions);
    }
}
