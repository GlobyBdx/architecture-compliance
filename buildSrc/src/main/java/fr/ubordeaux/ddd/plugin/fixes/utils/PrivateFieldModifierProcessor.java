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

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.ModifierKind;

public class PrivateFieldModifierProcessor extends AbstractProcessor<CtField<?>> {
    private final String name;

    public PrivateFieldModifierProcessor(String name) {
        this.name = name;
    }

    @Override
    public void process(CtField<?> element) {
        if (element.getSimpleName().compareTo(this.name) == 0) {
            element.setVisibility(ModifierKind.PRIVATE);
        }
    }
}
