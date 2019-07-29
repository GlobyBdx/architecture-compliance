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

package fr.ubordeaux.ddd.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.BasePlugin;

import fr.ubordeaux.ddd.plugin.compliance.ComplianceTask;
import fr.ubordeaux.ddd.plugin.conformance.ConformanceTask;
import fr.ubordeaux.ddd.plugin.description.DescriptionConfigurationTask;
import fr.ubordeaux.ddd.plugin.project.ProjectConfigurationTask;
import fr.ubordeaux.ddd.plugin.syntax.SyntaxConfigurationTask;

public final class CompliancePlugin implements Plugin<Project> {
    public static final String TASKS_GROUP = "Software Architecture Compliance Checking";

    private Project project;

    private PluginExtension pluginExtension;
    private SyntaxConfigurationTask syntaxConfigurationTask;
    private DescriptionConfigurationTask descriptionConfigurationTask;
    private ProjectConfigurationTask projectConfigurationTask;

    @Override
    public void apply(Project project) {
        this.project = project;
        this.pluginExtension = this.project.getExtensions().create(
                PluginExtension.EXTENSION_NAME, PluginExtension.class, this.project);
        this.createSyntaxConfigurationTask();
        this.createDescriptionConfigurationTask();
        this.createProjectConfigurationTask();
        this.createConformanceTask();
        this.createComplianceTask();
    }

    private void createSyntaxConfigurationTask() {
        this.syntaxConfigurationTask = this.project.getTasks().create(
                SyntaxConfigurationTask.TASK_NAME, SyntaxConfigurationTask.class, task -> {
                    task.setGroup(TASKS_GROUP);
                    task.setDescription("Configures syntax.");
                    task.dependsOn(BasePlugin.ASSEMBLE_TASK_NAME);
                    this.project.afterEvaluate(project -> {
                        task.setSyntax(this.pluginExtension.getSyntax());
                    });
                });
    }

    private void createDescriptionConfigurationTask() {
        this.descriptionConfigurationTask = this.project.getTasks().create(
                DescriptionConfigurationTask.TASK_NAME, DescriptionConfigurationTask.class, task -> {
                    task.setGroup(TASKS_GROUP);
                    task.setDescription("Configures description and imports metadata using given syntax.");
                    task.dependsOn(this.syntaxConfigurationTask);
                    this.project.afterEvaluate(project -> {
                        task.setDescription(this.pluginExtension.getDescription());
                    });
                });
    }

    private void createProjectConfigurationTask() {
        this.projectConfigurationTask = this.project.getTasks().create(
                ProjectConfigurationTask.TASK_NAME, ProjectConfigurationTask.class, task -> {
                    task.setGroup(TASKS_GROUP);
                    task.setDescription("Configures project and imports metadata from given bytecode.");
                    task.dependsOn(BasePlugin.ASSEMBLE_TASK_NAME);
                    this.project.afterEvaluate(project -> {
                        task.setProject(this.pluginExtension.getProject());
                    });
                });
    }

    private void createConformanceTask() {
        this.project.getTasks().create(ConformanceTask.TASK_NAME, ConformanceTask.class, task -> {
            task.setGroup(TASKS_GROUP);
            task.setDescription("Checks conformance between imported description and project metadata.");
            task.dependsOn(this.descriptionConfigurationTask, this.projectConfigurationTask);
        });
    }

    private void createComplianceTask() {
        this.project.getTasks().create(ComplianceTask.TASK_NAME, ComplianceTask.class, task -> {
            task.setGroup(TASKS_GROUP);
            task.setDescription("Checks compliance of imported metadata to given architectural constraints.");
            task.dependsOn(this.descriptionConfigurationTask, this.projectConfigurationTask);
            this.project.afterEvaluate(project -> {
                task.setRules(this.pluginExtension.getRules());
                task.setFixes(this.pluginExtension.getFixes());
            });
        });
    }
}
