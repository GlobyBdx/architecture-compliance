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
import com.tngtech.archunit.lang.syntax.elements.GivenMethods;
import com.tngtech.archunit.lang.syntax.elements.GivenMethodsConjunction;
import com.tngtech.archunit.lang.syntax.elements.MethodsShouldConjunction;

import static fr.ubordeaux.ddd.plugin.rules.utils.MethodUtils.*;

import java.util.Arrays;

public final class MethodRules {
    public static String getThatDescription(boolean isAnnotation, String methodSyntax) {
        StringBuilder description = new StringBuilder();
        description.append("Methods");
        if (methodSyntax != null) {
            description.append(" that are " + ((isAnnotation) ? "annotated" : "named") + " with " + methodSyntax);
        }
        return description.toString();
    }

    public static GivenMethodsConjunction thatAre(GivenMethods methods, boolean isAnnotation, String methodSyntax) {
        if (methodSyntax == null) {
            return (GivenMethodsConjunction)methods;
        }
        if (!isAnnotation) {
            return methods.that(areNamedWith(methodSyntax));
        }
        return methods.that(areAnnotatedWith(methodSyntax));
    }

    public static ArchRule shouldBePublic(GivenMethods methods, boolean isAnnotation, String methodSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, methodSyntax));
        description.append(" should be public");
        GivenMethodsConjunction that = thatAre(methods, isAnnotation, methodSyntax);
        MethodsShouldConjunction should = that.should().bePublic();
        return should.as(description.toString());
    }

    public static ArchRule shouldNotBePublic(GivenMethods methods, boolean isAnnotation, String methodSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, methodSyntax));
        description.append(" should not be public");
        GivenMethodsConjunction that = thatAre(methods, isAnnotation, methodSyntax);
        MethodsShouldConjunction should = that.should().notBePublic();
        return should.as(description.toString());
    }

    public static ArchRule shouldBeProtected(GivenMethods methods, boolean isAnnotation, String methodSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, methodSyntax));
        description.append(" should be protected");
        GivenMethodsConjunction that = thatAre(methods, isAnnotation, methodSyntax);
        MethodsShouldConjunction should = that.should().beProtected();
        return should.as(description.toString());
    }

    public static ArchRule shouldNotBeProtected(GivenMethods methods, boolean isAnnotation, String methodSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, methodSyntax));
        description.append(" should not be protected");
        GivenMethodsConjunction that = thatAre(methods, isAnnotation, methodSyntax);
        MethodsShouldConjunction should = that.should().notBeProtected();
        return should.as(description.toString());
    }

    public static ArchRule shouldBePrivate(GivenMethods methods, boolean isAnnotation, String methodSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, methodSyntax));
        description.append(" should be private");
        GivenMethodsConjunction that = thatAre(methods, isAnnotation, methodSyntax);
        MethodsShouldConjunction should = that.should().bePrivate();
        return should.as(description.toString());
    }

    public static ArchRule shouldNotBePrivate(GivenMethods methods, boolean isAnnotation, String methodSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, methodSyntax));
        description.append(" should not be private");
        GivenMethodsConjunction that = thatAre(methods, isAnnotation, methodSyntax);
        MethodsShouldConjunction should = that.should().notBePrivate();
        return should.as(description.toString());
    }

    public static ArchRule shouldBeFinal(GivenMethods methods, boolean isAnnotation, String methodSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, methodSyntax));
        description.append(" should be final");
        GivenMethodsConjunction that = thatAre(methods, isAnnotation, methodSyntax);
        MethodsShouldConjunction should = that.should().beFinal();
        return should.as(description.toString());
    }

    public static ArchRule shouldNotBeFinal(GivenMethods methods, boolean isAnnotation, String methodSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, methodSyntax));
        description.append(" should not be final");
        GivenMethodsConjunction that = thatAre(methods, isAnnotation, methodSyntax);
        MethodsShouldConjunction should = that.should().notBeFinal();
        return should.as(description.toString());
    }

    public static ArchRule shouldBeStatic(GivenMethods methods, boolean isAnnotation, String methodSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, methodSyntax));
        description.append(" should be static");
        GivenMethodsConjunction that = thatAre(methods, isAnnotation, methodSyntax);
        MethodsShouldConjunction should = that.should().beStatic();
        return should.as(description.toString());
    }

    public static ArchRule shouldNotBeStatic(GivenMethods methods, boolean isAnnotation, String methodSyntax) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, methodSyntax));
        description.append(" should not be static");
        GivenMethodsConjunction that = thatAre(methods, isAnnotation, methodSyntax);
        MethodsShouldConjunction should = that.should().notBeStatic();
        return should.as(description.toString());
    }

    public static ArchRule shouldOnlyAccessImmutableFields(GivenMethods methods,
            boolean isAnnotation, String methodSyntax, boolean areAnnotations, String... fieldSyntaxes) {
        StringBuilder description = new StringBuilder(getThatDescription(isAnnotation, methodSyntax));
        description.append(" should only access immutable fields");
        description.append((fieldSyntaxes.length != 0) ?
                ((areAnnotations) ? " annotated" : " named") + " with " + Arrays.toString(fieldSyntaxes)
                : "");
        GivenMethodsConjunction that = thatAre(methods, isAnnotation, methodSyntax);
        MethodsShouldConjunction should = that.should(accessOnlyImmutableFields(areAnnotations, fieldSyntaxes));
        return should.orShould().bePrivate().as(description.toString());
    }
}
