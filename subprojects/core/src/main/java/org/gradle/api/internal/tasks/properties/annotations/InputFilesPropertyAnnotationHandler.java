/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.api.internal.tasks.properties.annotations;

import org.gradle.api.internal.tasks.DeclaredTaskInputFileProperty;
import org.gradle.api.internal.tasks.PropertySpecFactory;
import org.gradle.api.internal.tasks.ValidationActions;
import org.gradle.api.internal.tasks.properties.PropertyValue;
import org.gradle.api.tasks.InputFiles;

import java.lang.annotation.Annotation;

public class InputFilesPropertyAnnotationHandler extends AbstractInputPropertyAnnotationHandler {
    public Class<? extends Annotation> getAnnotationType() {
        return InputFiles.class;
    }

    @Override
    protected DeclaredTaskInputFileProperty createFileSpec(PropertyValue propertyValue, PropertySpecFactory specFactory) {
        return specFactory.createInputFilesSpec(propertyValue, ValidationActions.INPUT_FILES_VALIDATOR);
    }
}
