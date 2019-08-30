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
import fr.ubordeaux.ddd.examples.ValueObjectOutsideDomainLayer;
import fr.ubordeaux.ddd.examples.domain.ValueObjectA;
import fr.ubordeaux.ddd.examples.domain.ValueObjectB;
import fr.ubordeaux.ddd.examples.domain.ValueObjectC;
import fr.ubordeaux.ddd.examples.domain.ValueObjectD;
import fr.ubordeaux.ddd.examples.domain.ValueObjectWithAccessIssue;
import fr.ubordeaux.ddd.examples.domain.ValueObjectWithGetterIssue;
import fr.ubordeaux.ddd.examples.domain.ValueObjectWithNonPrivateFields;
import fr.ubordeaux.ddd.examples.domain.ValueObjectWithNonPrivateSetters;
import fr.ubordeaux.ddd.examples.domain.ValueObjectWithoutField;
import fr.ubordeaux.ddd.examples.domain.ValueObjectWithoutGoodObjectMethodsA;
import fr.ubordeaux.ddd.examples.domain.ValueObjectWithoutGoodObjectMethodsB;
import fr.ubordeaux.ddd.examples.domain.ValueObjectWithoutObjectMethod;
import fr.ubordeaux.ddd.plugin.PluginConfiguration;
import fr.ubordeaux.ddd.plugin.project.Project;
import fr.ubordeaux.ddd.plugin.rules.ValueObjectRules;
import fr.ubordeaux.ddd.plugin.syntax.Syntax;
import fr.ubordeaux.ddd.tests.utils.TestUtils;

@RunWith(Parameterized.class)
public class ValueObjectTests {
    private static final Class<?>[] classes = {
            AggregateEntityFactoryRepositoryServiceValueObject.class,
            ValueObjectA.class,
            ValueObjectB.class,
            ValueObjectC.class,
            ValueObjectD.class,
            ValueObjectOutsideDomainLayer.class,
            ValueObjectWithAccessIssue.class,
            ValueObjectWithGetterIssue.class,
            ValueObjectWithNonPrivateFields.class,
            ValueObjectWithNonPrivateSetters.class,
            ValueObjectWithoutField.class,
            ValueObjectWithoutGoodObjectMethodsA.class,
            ValueObjectWithoutGoodObjectMethodsB.class,
            ValueObjectWithoutObjectMethod.class
    };

    private final ValueObjectRules rules;

    public ValueObjectTests(Syntax syntax) {
        if (PluginConfiguration.getProject() == null) {
            Project project = new Project(null, null, null);
            project.setClasses(new ClassFileImporter().importPackages("fr.ubordeaux.ddd.examples"));
            PluginConfiguration.setProject(project);
        }
        PluginConfiguration.setSyntax(syntax);
        this.rules = new ValueObjectRules();
    }

    @Parameters
    public static Collection<Syntax> generateParameters() {
        return TestUtils.generateSyntaxes();
    }

    @Test
    public void shouldResideInDomainLayer() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                ValueObjectOutsideDomainLayer.class
        };
        TestUtils.assertRule(rules.valueObjectsMustResideInDomainLayer, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeAggregates() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.valueObjectsMustNotAlsoBeAggregates, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeEntities() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.valueObjectsMustNotAlsoBeEntities, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeFactories() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.valueObjectsMustNotAlsoBeFactories, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeRepositories() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.valueObjectsMustNotAlsoBeRepositories, classes, exceptions);
    }

    @Test
    public void shouldNotAlsoBeServices() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class
        };
        TestUtils.assertRule(rules.valueObjectsMustNotAlsoBeServices, classes, exceptions);
    }

    @Test
    public void shouldHaveAtLeastOneField() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                ValueObjectWithoutField.class
        };
        TestUtils.assertRule(rules.valueObjectsMustHaveAtLeastOneField, classes, exceptions);
    }

    @Test
    public void shouldOverrideEqualsMethodAccessingAllFields() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                ValueObjectWithoutGoodObjectMethodsA.class,
                ValueObjectWithoutGoodObjectMethodsB.class,
                ValueObjectWithoutObjectMethod.class
        };
        TestUtils.assertRule(rules.valueObjectsMustOverrideEqualsMethodAccessingAllFields, classes, exceptions);
    }

    @Test
    public void shouldOverrideHashCodeMethodAccessingAllFields() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                ValueObjectWithoutGoodObjectMethodsA.class,
                ValueObjectWithoutGoodObjectMethodsB.class,
                ValueObjectWithoutObjectMethod.class
        };
        TestUtils.assertRule(rules.valueObjectsMustOverrideHashCodeMethodAccessingAllFields, classes, exceptions);
    }

    @Test
    public void shouldOverrideToStringMethodAccessingAllFields() {
        Class<?>[] exceptions = {
                AggregateEntityFactoryRepositoryServiceValueObject.class,
                ValueObjectWithoutGoodObjectMethodsA.class,
                ValueObjectWithoutGoodObjectMethodsB.class,
                ValueObjectWithoutObjectMethod.class
        };
        TestUtils.assertRule(rules.valueObjectsMustOverrideToStringMethodAccessingAllFields, classes, exceptions);
    }

    @Test
    public void shouldBePrivate() {
        Class<?>[] exceptions = {
                ValueObjectWithNonPrivateFields.class
        };
        TestUtils.assertRule(rules.valueObjectsFieldsMustBePrivate, classes, exceptions);
    }

    @Test
    public void shouldBeFinal() {
        Class<?>[] exceptions = {
                ValueObjectB.class,
                ValueObjectC.class,
                ValueObjectD.class,
                ValueObjectOutsideDomainLayer.class,
                ValueObjectWithAccessIssue.class,
                ValueObjectWithGetterIssue.class,
                ValueObjectWithNonPrivateFields.class,
                ValueObjectWithNonPrivateSetters.class,
                ValueObjectWithoutGoodObjectMethodsA.class,
                ValueObjectWithoutGoodObjectMethodsB.class,
                ValueObjectWithoutObjectMethod.class
        };
        TestUtils.assertRule(rules.valueObjectsFieldsMustBeFinal, classes, exceptions);
    }

    @Test
    public void shouldBeImmutables() {
        Class<?>[] exceptions = {
                ValueObjectC.class,
                ValueObjectOutsideDomainLayer.class,
                ValueObjectWithAccessIssue.class,
                ValueObjectWithGetterIssue.class,
                ValueObjectWithNonPrivateFields.class,
                ValueObjectWithNonPrivateSetters.class,
                ValueObjectWithoutGoodObjectMethodsA.class,
                ValueObjectWithoutGoodObjectMethodsB.class,
                ValueObjectWithoutObjectMethod.class
        };
        TestUtils.assertRule(rules.valueObjectsFieldsMustBeImmutables, classes, exceptions);
    }

    @Test
    public void shouldHavePrivateSetters() {
        Class<?>[] exceptions = {
                ValueObjectWithNonPrivateSetters.class
        };
        TestUtils.assertRule(rules.valueObjectsFieldsMustHavePrivateSetters, classes, exceptions);
    }

    @Test
    public void shouldHaveSpecificGetters() {
        Class<?>[] exceptions = {
                ValueObjectWithGetterIssue.class
        };
        TestUtils.assertRule(rules.valueObjectsFieldsMustHaveSpecificGetters, classes, exceptions);
    }

    @Test
    public void shouldHaveSpecificAccesses() {
        Class<?>[] exceptions = {
                ValueObjectWithAccessIssue.class,
                ValueObjectWithoutGoodObjectMethodsB.class
        };
        TestUtils.assertRule(rules.valueObjectsFieldsMustHaveSpecificAccesses, classes, exceptions);
    }
}
