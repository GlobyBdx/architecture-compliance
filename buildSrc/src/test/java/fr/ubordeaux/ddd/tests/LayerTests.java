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

import fr.ubordeaux.ddd.examples.anticorruption.TokenAnticorruptionLayer;
import fr.ubordeaux.ddd.examples.anticorruption.application.domain.infrastructure.presentation.TokenLayers;
import fr.ubordeaux.ddd.examples.application.TokenApplicationLayer;
import fr.ubordeaux.ddd.examples.domain.TokenDomainLayer;
import fr.ubordeaux.ddd.examples.domain.TokenWithAnticorruptionLayerAccess;
import fr.ubordeaux.ddd.examples.domain.TokenWithApplicationLayerAccess;
import fr.ubordeaux.ddd.examples.domain.TokenWithInfrastructureLayerAccess;
import fr.ubordeaux.ddd.examples.domain.TokenWithPresentationLayerAccess;
import fr.ubordeaux.ddd.examples.infrastructure.TokenInfrastructureLayer;
import fr.ubordeaux.ddd.examples.presentation.TokenPresentationLayer;
import fr.ubordeaux.ddd.plugin.PluginConfiguration;
import fr.ubordeaux.ddd.plugin.project.Project;
import fr.ubordeaux.ddd.plugin.rules.LayerRules;
import fr.ubordeaux.ddd.plugin.syntax.Syntax;
import fr.ubordeaux.ddd.tests.utils.TestUtils;

@RunWith(Parameterized.class)
public class LayerTests {
    private static final Class<?>[] classes = {
            TokenAnticorruptionLayer.class,
            TokenApplicationLayer.class,
            TokenDomainLayer.class,
            TokenInfrastructureLayer.class,
            TokenLayers.class,
            TokenPresentationLayer.class,
            TokenWithAnticorruptionLayerAccess.class,
            TokenWithApplicationLayerAccess.class,
            TokenWithInfrastructureLayerAccess.class,
            TokenWithPresentationLayerAccess.class
    };

    private final LayerRules rules;

    public LayerTests(Syntax syntax) {
        if (PluginConfiguration.getProject() == null) {
            Project project = new Project(null, null, null);
            project.setClasses(new ClassFileImporter().importPackages("fr.ubordeaux.ddd.examples"));
            PluginConfiguration.setProject(project);
        }
        PluginConfiguration.setSyntax(syntax);
        this.rules = new LayerRules();
    }

    @Parameters
    public static Collection<Syntax> generateParameters() {
        return TestUtils.generateSyntaxes();
    }

    @Test
    public void shouldNotResideInAnotherLayer() {
        Class<?>[] exceptions = {
                TokenLayers.class
        };
        TestUtils.assertRule(rules.anticorruptionLayerClassesMustNotResideInAnotherLayer, classes, exceptions);
        TestUtils.assertRule(rules.applicationLayerClassesMustNotResideInAnotherLayer, classes, exceptions);
        TestUtils.assertRule(rules.domainLayerClassesMustNotResideInAnotherLayer, classes, exceptions);
        TestUtils.assertRule(rules.infrastructureLayerClassesMustNotResideInAnotherLayer, classes, exceptions);
        TestUtils.assertRule(rules.presentationLayerClassesMustNotResideInAnotherLayer, classes, exceptions);
    }

    @Test
    public void shouldBeRespected() {
        Class<?>[] exceptions = {
                TokenAnticorruptionLayer.class,
                TokenApplicationLayer.class,
                TokenInfrastructureLayer.class,
                TokenPresentationLayer.class,
                TokenWithAnticorruptionLayerAccess.class,
                TokenWithApplicationLayerAccess.class,
                TokenWithInfrastructureLayerAccess.class,
                TokenWithPresentationLayerAccess.class
        };
        TestUtils.assertRule(rules.anticorruptionLayerDependenciesMustBeRespected, classes, exceptions);
        TestUtils.assertRule(rules.applicationLayerDependenciesMustBeRespected, classes, exceptions);
        TestUtils.assertRule(rules.infrastructureLayerDependenciesMustBeRespected, classes, exceptions);
        TestUtils.assertRule(rules.presentationLayerDependenciesMustBeRespected, classes, exceptions);
    }
}
