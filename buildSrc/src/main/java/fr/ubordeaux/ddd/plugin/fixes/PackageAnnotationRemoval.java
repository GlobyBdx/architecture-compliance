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

import fr.ubordeaux.ddd.plugin.fixes.utils.AnnotationManager;
import fr.ubordeaux.ddd.plugin.fixes.utils.QuickFixUtils;
import fr.ubordeaux.ddd.plugin.project.Project;

import fr.ubordeaux.ddd.plugin.description.Description;

public final class PackageAnnotationRemoval extends AbstractAnnotationRemoval {
    public PackageAnnotationRemoval(String annotation) {
        super(annotation);
    }

    @Override
    public void use(String name, Description description, Project project) {
        if (description == null) {
            QuickFixUtils.deletePackageInfoFile(name, project.getFullPath(), project);
            QuickFixUtils.createPackageInfoFile(name, project.getFullPath(), project);
        }
        else {
            AnnotationManager.manage(
                    description, QuickFixUtils.getPackageInfoName(name, project), this.getAnnotation(), false);
            description.update();
        }
    }
}
