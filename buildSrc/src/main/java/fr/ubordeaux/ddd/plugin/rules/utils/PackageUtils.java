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

package fr.ubordeaux.ddd.plugin.rules.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tngtech.archunit.core.domain.JavaClass;

import fr.ubordeaux.ddd.plugin.PluginConfiguration;

public class PackageUtils {
    private static final Set<JavaClass> packageClasses = new HashSet<>();

    public static String[] getSubPackagesAnnotatedWith(String annotation) {
        return getPackagesAnnotatedWith(annotation, true);
    }

    public static String[] getPackagesAnnotatedWith(String annotation) {
        return getPackagesAnnotatedWith(annotation, false);
    }

    public static String[] getPackagesAnnotatedWith(String annotation, boolean sub) {
        if (PluginConfiguration.getDescription() == null) {
            return getPackagesAnnotatedWithoutArchitecture(annotation, sub);
        }
        if (!PluginConfiguration.getDescription().getPackages().containsKey(annotation)) {
            return new String[0];
        }
        if (!sub) {
            return PluginConfiguration.getDescription().getPackages().get(annotation).toArray(new String[0]);
        }
        List<String> packages = new ArrayList<>();
        for (String annotatedPackage : PluginConfiguration.getDescription().getPackages().get(annotation)) {
            packages.add(annotatedPackage + "..");
        }
        return packages.toArray(new String[0]);
    }

    public static String[] getPackagesAnnotatedWithoutArchitecture(String annotation, boolean sub) {
        if (packageClasses.isEmpty()) {
            updatePackageClasses();
        }
        List<String> packages = new ArrayList<>();
        for (JavaClass packageClass : packageClasses) {
            if (packageClass.isAnnotatedWith(annotation)) {
                packages.add(packageClass.getPackageName() + ((sub) ? ".." : ""));
            }
        }
        return packages.toArray(new String[0]);
    }

    public static String[] getPackagesNamedWith(String name) {
        List<String> packages = new ArrayList<>();
        for (JavaClass importedClass : PluginConfiguration.getProject().getClasses()) {
            if (importedClass.getPackageName().compareTo(name) == 0
                    || importedClass.getPackageName().endsWith("." + name)) {
                packages.add(importedClass.getPackageName());
            }
        }
        return packages.toArray(new String[0]);
    }

    public static void updatePackageClasses() {
        packageClasses.clear();
        for (JavaClass importedClass : PluginConfiguration.getProject().getClasses()) {
            if (importedClass.getSimpleName().compareTo("package-info") == 0) {
                packageClasses.add(importedClass);
            }
        }
    }
}
