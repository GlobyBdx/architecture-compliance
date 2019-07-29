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

package fr.ubordeaux.ddd.plugin.fixes;

import java.util.List;
import java.util.Scanner;

import com.tngtech.archunit.lang.ArchRule;

import fr.ubordeaux.ddd.plugin.description.Description;
import fr.ubordeaux.ddd.plugin.project.Project;

public final class QuickFixer {
    private final Description description;
    private final Project project;
    private final QuickFixSelector selector;

    private static Scanner scanner;

    public QuickFixer(Description description, Project project, QuickFixSelector selector) {
        this.description = description;
        this.project = project;
        this.selector = selector;
    }

    public void fix(String name, ArchRule rule, List<String> details) {
        QuickFix[] fixes = this.selector.select(name);
        if (fixes == null || fixes.length == 0 || !askConfirmation("Do you want to enter fix mode?")) {
            return;
        }
        QuickFix fix = null;
        this.printFixes(fixes);
        boolean confirmation = details.size() != 1
                && askConfirmation("Do you want to use the same fix on each violation?");
        String message = "Which fix do you want to use on "
                + ((confirmation) ? "these violations" : "this violation") + "?";
        for (String detail : details) {
            if (!confirmation) {
                System.out.println(detail);
            }
            if (fix == null || !confirmation) {
                int index = askNumeric(message, 0, fixes.length);
                if (index == 0 && !confirmation) {
                    continue;
                }
                else if (index == 0) {
                    return;
                }
                else {
                    fix = fixes[index - 1];
                }
            }
            fix.use(this.getName(detail), this.description, this.project);
        }
    }

    private void printFixes(QuickFix[] fixes) {
        int index = 0;
        System.out.println("Available fixes:");
        this.printFix(index, "Does nothing.");
        for (; index < fixes.length; ++index) {
            this.printFix(index + 1, fixes[index].getDescription());
        }
    }

    private void printFix(int index, String description) {
        System.out.println("(" + index + ") - " + description);
    }

    private String getName(String detail) {
        return detail.substring(detail.indexOf("<") + 1, detail.indexOf(">"));
    }

    public static boolean askConfirmation(String message) {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println(message + " (y/n)");
            String input = scanner.nextLine().toLowerCase();
            if (input.compareTo("y") == 0 || input.compareTo("yes") == 0) {
                return true;
            }
            if (input.compareTo("n") == 0 || input.compareTo("no") == 0) {
                return false;
            }
        }
    }

    public static int askNumeric(String message, int from, int to) {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println(message + " (" + from + "-" + to + ")");
            String input = scanner.nextLine();
            try {
                int numeric = Integer.parseInt(input);
                if (numeric >= from && numeric <= to) {
                    return numeric;
                }
            } catch (NumberFormatException e) {}
        }
    }
}
