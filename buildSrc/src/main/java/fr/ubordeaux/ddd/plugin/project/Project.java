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

package fr.ubordeaux.ddd.plugin.project;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import com.tngtech.archunit.core.domain.JavaClasses;

public final class Project {
    private final String directoryPath;
    private final String folderPath;
    private final String[] packages;

    private final ProjectImporter importer;
    private JavaClasses classes;

    public Project(String directoryPath, String folderPath, String[] packages) {
        this.directoryPath = directoryPath;
        this.folderPath = folderPath;
        this.packages = packages;
        this.importer = new ProjectImporter(this);
        this.classes = null;
        this.update();
    }

    public String getDirectoryPath() {
        return this.directoryPath;
    }

    public String getFolderPath() {
        return this.folderPath;
    }

    public String getFullPath() {
        return this.directoryPath + File.separator + this.folderPath + File.separator;
    }

    public String[] getPackages() {
        return this.packages;
    }

    public JavaClasses getClasses() {
        return this.classes;
    }

    public void setClasses(JavaClasses classes) {
        this.classes = classes;
    }

    public void update() {
        this.importer.clearProject();
        this.importer.importProject();
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Project
                && this.directoryPath.compareTo(((Project)object).directoryPath) == 0
                && this.folderPath.compareTo(((Project)object).folderPath) == 0
                && this.packages.equals(((Project)object).packages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.directoryPath, this.folderPath, this.packages);
    }

    @Override
    public String toString() {
        return "\nDirectory Path: " + this.directoryPath
                + "\nFolder Path: " + this.folderPath
                + "\nPackages: " + Arrays.toString(this.packages)
                + "\nClasses: " + this.classes.size();
    }
}
