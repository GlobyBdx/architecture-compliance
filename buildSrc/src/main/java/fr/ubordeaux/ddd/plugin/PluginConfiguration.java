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

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import fr.ubordeaux.ddd.plugin.description.Description;
import fr.ubordeaux.ddd.plugin.project.Project;
import fr.ubordeaux.ddd.plugin.rules.AggregateRules;
import fr.ubordeaux.ddd.plugin.rules.EntityRules;
import fr.ubordeaux.ddd.plugin.rules.FactoryRules;
import fr.ubordeaux.ddd.plugin.rules.LayerRules;
import fr.ubordeaux.ddd.plugin.rules.RepositoryRules;
import fr.ubordeaux.ddd.plugin.rules.ServiceRules;
import fr.ubordeaux.ddd.plugin.rules.ValueObjectRules;
import fr.ubordeaux.ddd.plugin.syntax.Syntax;

public final class PluginConfiguration {
    private static Syntax SYNTAX;
    private static Description DESCRIPTION;
    private static Project PROJECT;

    private static final Class<?>[] SETS = {
            AggregateRules.class,
            EntityRules.class,
            FactoryRules.class,
            LayerRules.class,
            RepositoryRules.class,
            ServiceRules.class,
            ValueObjectRules.class
    };

    private static final List<String> IMMUTABLES = Arrays.asList(new String[] {
            Boolean.class.getName(),
            Byte.class.getName(),
            Character.class.getName(),
            Collections.class.getName(),
            Double.class.getName(),
            File.class.getName(),
            Float.class.getName(),
            Integer.class.getName(),
            Long.class.getName(),
            Short.class.getName(),
            StackTraceElement.class.getName(),
            String.class.getName(),
            UUID.class.getName()
    });

    public static Syntax getSyntax() {
        return SYNTAX;
    }

    public static void setSyntax(Syntax syntax) {
        SYNTAX = syntax;
    }

    public static Description getDescription() {
        return DESCRIPTION;
    }

    public static void setDescription(Description description) {
        DESCRIPTION = description;
    }

    public static Project getProject() {
        return PROJECT;
    }

    public static void setProject(Project project) {
        PROJECT = project;
    }

    public static Class<?>[] getSets() {
        return SETS;
    }

    public static List<String> getImmutables() {
        return IMMUTABLES;
    }
}
