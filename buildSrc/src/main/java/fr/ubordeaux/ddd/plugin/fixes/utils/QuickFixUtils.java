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

package fr.ubordeaux.ddd.plugin.fixes.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.tngtech.archunit.core.domain.JavaClass;

import fr.ubordeaux.ddd.plugin.project.Project;

public final class QuickFixUtils {
    public static void createPackageInfoFile(String name, String path, Project project) {
        File packageInfoFile = new File(path + getPackageInfoPath(name, project));
        if (!packageInfoFile.exists()) {
            try {
                Writer writer = new FileWriter(packageInfoFile);
                writer.write("package " + QuickFixUtils.getClassPackageName(name, project) + ";");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deletePackageInfoFile(String name, String path, Project project) {
        new File(path + getPackageInfoPath(name, project)).delete();
    }

    public static String getPackageInfoPath(String name, Project project) {
        return getPackageInfoName(name, project).replace(".", File.separator) + ".java";
    }

    public static String getPackageInfoName(String name, Project project) {
        return getClassPackageName(name, project) + ".package-info";
    }

    public static String getClassPackageName(String name, Project project) {
        return getQualifiedClassName(name, project).substring(0, name.lastIndexOf("."));
    }

    public static JavaClass getJavaClass(String name, Project project) {
        return project.getClasses().get(getQualifiedClassName(name, project));
    }

    public static String getClassFilePath(String name, Project project) {
        return getQualifiedClassName(name, project).replace(".", File.separator) + ".java";
    }

    public static String getSimpleClassName(String name, Project project) {
        return removeClassPackageName(getQualifiedClassName(name, project));
    }

    public static String getQualifiedClassName(String name, Project project) {
        return removeClassMemberName(removeParameterTypes(name), project);
    }

    public static String getClassMemberName(String name) {
        return removeClassPackageName(removeParameterTypes(name));
    }

    private static String removeClassPackageName(String name) {
        return name.substring(name.lastIndexOf(".") + 1, name.length());
    }

    private static String removeClassMemberName(String name, Project project) {
        return (!project.getClasses().contain(name)) ? name.substring(0, name.lastIndexOf(".")) : name;
    }

    private static String removeParameterTypes(String name) {
        return (name.contains("(")) ? name.substring(0, name.indexOf("(")) : name;
    }
}
