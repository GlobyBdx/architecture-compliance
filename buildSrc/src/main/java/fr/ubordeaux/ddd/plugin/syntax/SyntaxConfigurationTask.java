/*
 * Copyright 2019 Benoit Faget. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * @author Benoit Faget
 * @version 0.1.0
 */

package fr.ubordeaux.ddd.plugin.syntax;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import fr.ubordeaux.ddd.plugin.PluginConfiguration;

public class SyntaxConfigurationTask extends DefaultTask {
    public static final String TASK_NAME = "syntax";

    private SyntaxExtension syntax;

    public void setSyntax(SyntaxExtension syntax) {
        this.syntax = syntax;
    }

    @TaskAction
    public void configure() {
        System.out.println(this.getDescription());
        PluginConfiguration.setSyntax(new Syntax(
                new Classes(
                        this.syntax.getClasses().getAggregate(),
                        this.syntax.getClasses().getEntity(),
                        this.syntax.getClasses().getFactory(),
                        this.syntax.getClasses().getRepository(),
                        this.syntax.getClasses().getService(),
                        this.syntax.getClasses().getValueObject(),
                        this.syntax.getClasses().getAreAnnotations()),
                new Fields(
                        this.syntax.getFields().getEntityId(),
                        this.syntax.getFields().getImmutable(),
                        this.syntax.getFields().getAreAnnotations()),
                new Methods(
                        this.syntax.getMethods().getGetter(),
                        this.syntax.getMethods().getSetter(),
                        this.syntax.getMethods().getAreAnnotations()),
                new Packages(
                        this.syntax.getPackages().getAnticorruption(),
                        this.syntax.getPackages().getApplication(),
                        this.syntax.getPackages().getDomain(),
                        this.syntax.getPackages().getInfrastructure(),
                        this.syntax.getPackages().getPresentation(),
                        this.syntax.getPackages().getAreAnnotations())));
        System.out.println(PluginConfiguration.getSyntax().toString());
    }
}
