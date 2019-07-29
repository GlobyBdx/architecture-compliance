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

package fr.ubordeaux.ddd.plugin.compliance;

import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.tasks.TaskAction;

import fr.ubordeaux.ddd.plugin.PluginConfiguration;
import fr.ubordeaux.ddd.plugin.fixes.QuickFixSelector;
import fr.ubordeaux.ddd.plugin.fixes.QuickFixer;

public class ComplianceTask extends DefaultTask {
    public static final String TASK_NAME = "compliance";

    private String[] rules;
    private Boolean fixes;

    public void setRules(String[] rules) {
        this.rules = rules;
    }

    public void setFixes(Boolean fixes) {
        this.fixes = fixes;
    }

    @TaskAction
    public void check() {
        System.out.println(this.getDescription());
        if (this.fixes && !QuickFixer.askConfirmation("\nFixes have been enabled, do you want to continue?")) {
            return;
        }
        ComplianceChecker checker = new ComplianceChecker(
                PluginConfiguration.getProject(),
                PluginConfiguration.getSets(),
                this.rules,
                (this.fixes) ?
                        new QuickFixer(
                                PluginConfiguration.getDescription(),
                                PluginConfiguration.getProject(),
                                new QuickFixSelector(PluginConfiguration.getSyntax()))
                        : null);
        int violations = checker.check();
        if (violations != 0) {
            throw new GradleException("Rules have revealed " + violations
                    + ((violations != 1) ? " violations" : " violation") + ".");
        }
        System.out.println("\nRules have not revealed any violation.");
    }
}
