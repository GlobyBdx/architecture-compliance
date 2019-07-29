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

package fr.ubordeaux.ddd.plugin.description;

import java.io.File;

import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.tasks.TaskAction;

import fr.ubordeaux.ddd.plugin.PluginConfiguration;

public class DescriptionConfigurationTask extends DefaultTask {
    public static final String TASK_NAME = "description";

    private DescriptionExtension description;

    public void setDescription(DescriptionExtension description) {
        this.description = description;
    }

    @TaskAction
    public void configure() {
        System.out.println(this.getDescription());
        if (this.description.getPaths().length == 0) {
            return;
        }
        PluginConfiguration.setDescription(new Description(
                this.description.getPaths(),
                this.description.getFromPackages(),
                this.description.getAsXml(),
                PluginConfiguration.getSyntax()));
        for (String path : PluginConfiguration.getDescription().getPaths()) {
            this.isFile(path);
        }
        System.out.println(PluginConfiguration.getDescription().toString());
    }

    private void isFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new GradleException("'" + path + "' does not exist.");
        }
        if (!file.isFile()) {
            throw new GradleException("'" + path + "' is not a file.");
        }
    }
}
