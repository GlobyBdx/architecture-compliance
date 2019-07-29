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

package fr.ubordeaux.ddd.plugin.conformance;

import java.util.List;
import java.util.Map;

import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.tasks.TaskAction;

import fr.ubordeaux.ddd.plugin.PluginConfiguration;

public class ConformanceTask extends DefaultTask {
    public static final String TASK_NAME = "conformance";

    @TaskAction
    public void check() {
        System.out.println(this.getDescription());
        if (PluginConfiguration.getDescription() == null) {
            throw new GradleException("Description has not been found.");
        }
        ConformanceChecker checker = new ConformanceChecker(
                PluginConfiguration.getDescription(),
                PluginConfiguration.getProject());
        List<String> classes = checker.checkClasses();
        if (!classes.isEmpty()) {
            this.printMessage("classes", classes, PluginConfiguration.getDescription().getClasses());
        }
        List<String> fields = checker.checkFields();
        if (!fields.isEmpty()) {
            this.printMessage("fields", fields, PluginConfiguration.getDescription().getFields());
        }
        List<String> methods = checker.checkMethods();
        if (!methods.isEmpty()) {
            this.printMessage("methods", methods, PluginConfiguration.getDescription().getMethods());
        }
        if (classes.size() + fields.size() + methods.size() > 0) {
            throw new GradleException("Project does not contain elements to assert description.");
        }
        System.out.println("\nProject does contain elements to assert description.");
    }

    private void printMessage(String type, List<String> list, Map<String, List<String>> map) {
        System.out.println("\nProject does not contain " + type + " to assert description (at most "
                + ((1 - (double)list.size() / (double)getMapSize(map)) * 100) + "% can be asserted).\n"
                + list.toString());
    }

    private static int getMapSize(Map<String, List<String>> map) {
        int size = 0;
        for (List<String> list : map.values()) {
            size += list.size();
        }
        return size;
    }
}
