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

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.elements.FieldsShouldConjunction;
import com.tngtech.archunit.lang.syntax.elements.GivenFields;
import com.tngtech.archunit.lang.syntax.elements.GivenFieldsConjunction;

import static fr.ubordeaux.ddd.plugin.rules.utils.FieldUtils.*;

import java.util.Arrays;

public final class FieldRules {
    public static String getThatDescription(boolean isAnnotation, String fieldSyntax) {
        StringBuilder description = new StringBuilder();
        description.append("Fields");
        if (fieldSyntax != null) {
            description.append(" that are " + ((isAnnotation) ? "annotated" : "named") + " with " + fieldSyntax);
        }
        return description.toString();
    }

    public static GivenFieldsConjunction thatAre(GivenFields fields, boolean isAnnotation, String fieldSyntax) {
        if (fieldSyntax == null) {
            return (GivenFieldsConjunction)fields;
        }
        if (!isAnnotation) {
            return fields.that(areNamedWith(fieldSyntax));
        }
        return fields.that(areAnnotatedWith(fieldSyntax));
    }

    public static ArchRule shouldBePublic(GivenFields fields, boolean isAnnotation, String fieldSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, fieldSyntax));
        description.append(" should be public");
        GivenFieldsConjunction that = thatAre(fields, isAnnotation, fieldSyntax);
        FieldsShouldConjunction should = that.should().bePublic();
        return should.as(description.toString());
    }

    public static ArchRule shouldNotBePublic(GivenFields fields, boolean isAnnotation, String fieldSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, fieldSyntax));
        description.append(" should not be public");
        GivenFieldsConjunction that = thatAre(fields, isAnnotation, fieldSyntax);
        FieldsShouldConjunction should = that.should().notBePublic();
        return should.as(description.toString());
    }

    public static ArchRule shouldBeProtected(GivenFields fields, boolean isAnnotation, String fieldSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, fieldSyntax));
        description.append(" should be protected");
        GivenFieldsConjunction that = thatAre(fields, isAnnotation, fieldSyntax);
        FieldsShouldConjunction should = that.should().beProtected();
        return should.as(description.toString());
    }

    public static ArchRule shouldNotBeProtected(GivenFields fields, boolean isAnnotation, String fieldSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, fieldSyntax));
        description.append(" should not be protected");
        GivenFieldsConjunction that = thatAre(fields, isAnnotation, fieldSyntax);
        FieldsShouldConjunction should = that.should().notBeProtected();
        return should.as(description.toString());
    }

    public static ArchRule shouldBePrivate(GivenFields fields, boolean isAnnotation, String fieldSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, fieldSyntax));
        description.append(" should be private");
        GivenFieldsConjunction that = thatAre(fields, isAnnotation, fieldSyntax);
        FieldsShouldConjunction should = that.should().bePrivate();
        return should.as(description.toString());
    }

    public static ArchRule shouldNotBePrivate(GivenFields fields, boolean isAnnotation, String fieldSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, fieldSyntax));
        description.append(" should not be private");
        GivenFieldsConjunction that = thatAre(fields, isAnnotation, fieldSyntax);
        FieldsShouldConjunction should = that.should().notBePrivate();
        return should.as(description.toString());
    }

    public static ArchRule shouldBeFinal(GivenFields fields, boolean isAnnotation, String fieldSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, fieldSyntax));
        description.append(" should be final");
        GivenFieldsConjunction that = thatAre(fields, isAnnotation, fieldSyntax);
        FieldsShouldConjunction should = that.should().beFinal();
        return should.as(description.toString());
    }

    public static ArchRule shouldNotBeFinal(GivenFields fields, boolean isAnnotation, String fieldSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, fieldSyntax));
        description.append(" should not be final");
        GivenFieldsConjunction that = thatAre(fields, isAnnotation, fieldSyntax);
        FieldsShouldConjunction should = that.should().notBeFinal();
        return should.as(description.toString());
    }

    public static ArchRule shouldBeStatic(GivenFields fields, boolean isAnnotation, String fieldSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, fieldSyntax));
        description.append(" should be static");
        GivenFieldsConjunction that = thatAre(fields, isAnnotation, fieldSyntax);
        FieldsShouldConjunction should = that.should().beStatic();
        return should.as(description.toString());
    }

    public static ArchRule shouldNotBeStatic(GivenFields fields, boolean isAnnotation, String fieldSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, fieldSyntax));
        description.append(" should not be static");
        GivenFieldsConjunction that = thatAre(fields, isAnnotation, fieldSyntax);
        FieldsShouldConjunction should = that.should().notBeStatic();
        return should.as(description.toString());
    }

    public static ArchRule shouldBeImmutable(GivenFields fields, boolean isAnnotation, String fieldSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, fieldSyntax));
        description.append(" should be immutable");
        GivenFieldsConjunction that = thatAre(fields, isAnnotation, fieldSyntax);
        FieldsShouldConjunction should = that.should(beImmutables);
        return should.as(description.toString());
    }

    public static ArchRule shouldNotBeImmutable(GivenFields fields, boolean isAnnotation, String fieldSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, fieldSyntax));
        description.append(" should not be immutable");
        GivenFieldsConjunction that = thatAre(fields, isAnnotation, fieldSyntax);
        FieldsShouldConjunction should = that.should(notBeImmutables);
        return should.as(description.toString());
    }

    public static ArchRule shouldOnlyBeDeclaredInClasses(GivenFields fields,
            boolean isAnnotation, String fieldSyntax, boolean areAnnotations, String... classSyntaxes) {
        if (classSyntaxes.length == 0) return null;
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, fieldSyntax));
        description.append(" should only be declared in classes ");
        description.append(((areAnnotations) ? "annotated" : "named") + " with " + Arrays.toString(classSyntaxes));
        GivenFieldsConjunction that = thatAre(fields, isAnnotation, fieldSyntax);
        FieldsShouldConjunction should = ((areAnnotations) ?
                that.should().beDeclaredInClassesThat(ClassUtils.areAnnotatedWith(classSyntaxes[0]))
                : that.should().beDeclaredInClassesThat(ClassUtils.areNamedWith(classSyntaxes[0])));
        for (int index = 1; index < classSyntaxes.length; ++index) {
            should = ((areAnnotations) ?
                    should.orShould().beDeclaredInClassesThat(ClassUtils.areAnnotatedWith(classSyntaxes[index]))
                    : should.orShould().beDeclaredInClassesThat(ClassUtils.areNamedWith(classSyntaxes[index])));
        }
        return should.as(description.toString());
    }
}
