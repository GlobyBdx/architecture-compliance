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

import java.lang.annotation.Annotation;

import fr.ubordeaux.ddd.plugin.project.Project;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtAnnotation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.reference.CtPackageReference;
import spoon.reflect.reference.CtTypeReference;

public class ClassAnnotationInclusionProcessor extends AbstractProcessor<CtClass<?>> {
    private final String name;
    private final String annotation;
    private final Project project;

    public ClassAnnotationInclusionProcessor(String name, String annotation, Project project) {
        this.name = name;
        this.annotation = annotation;
        this.project = project;
    }

    @Override
    public void process(CtClass<?> element) {
        if (element.getQualifiedName().compareTo(this.name) == 0) {
            CtPackageReference packageReference = getFactory().Core().createPackageReference();
            packageReference.setSimpleName(QuickFixUtils.getClassPackageName(this.annotation, project));
            CtTypeReference<? extends Annotation> typeReference = getFactory().Core().createTypeReference();
            typeReference.setSimpleName(QuickFixUtils.getSimpleClassName(this.annotation, project));
            typeReference.setPackage(packageReference);
            CtAnnotation<Annotation> annotation = getFactory().Core().createAnnotation();
            annotation.setAnnotationType(typeReference);
            element.addAnnotation(annotation);
        }
    }
}
