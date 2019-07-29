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

import java.lang.reflect.InvocationTargetException;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.FailureReport;

import fr.ubordeaux.ddd.plugin.fixes.QuickFixer;
import fr.ubordeaux.ddd.plugin.project.Project;

public final class ComplianceChecker {
    private final Project project;
    private final Class<?>[] sets;
    private final String[] rules;
    private final QuickFixer fixer;

    public ComplianceChecker(Project project, Class<?>[] sets, String[] rules, QuickFixer fixer) {
        this.project = project;
        this.sets = sets;
        this.rules = rules;
        this.fixer = fixer;
    }

    public int check() {
        int violations = 0;
        for (int index = 0; index < this.rules.length; ++index) {
            violations += this.evaluate(this.rules[index], this.get(this.rules[index], index + 1));
        }
        return violations;
    }

    private int evaluate(String name, ArchRule rule) {
        if (rule == null) {
            return 0;
        }
        FailureReport report = rule.evaluate(this.project.getClasses()).getFailureReport();
        if (!report.isEmpty()) {
            System.out.println(report.toString());
            if (this.fixer != null) {
                this.fixer.fix(name, rule, report.getDetails());
            }
        }
        return report.getDetails().size();
    }

    private ArchRule get(String name, int index) {
        String message = " [" + index + "/" + this.rules.length + "]";
        for (Class<?> set : this.sets) {
            try {
                Object instance = set.getDeclaredConstructor().newInstance();
                Object field = instance.getClass().getDeclaredField(name).get(instance);
                if (field instanceof ArchRule) {
                    System.out.println("\nRule '" + name + "' has been found." + message);
                    return (ArchRule)field;
                }
            } catch (InstantiationException | InvocationTargetException | NoSuchMethodException
                    | IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException | SecurityException e) {}
        }
        System.out.println("\nRule '" + name + "' has not been found." + message);
        return null;
    }
}
