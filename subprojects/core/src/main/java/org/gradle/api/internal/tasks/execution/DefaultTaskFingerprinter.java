/*
 * Copyright 2018 the original author or authors.
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

package org.gradle.api.internal.tasks.execution;

import com.google.common.collect.ImmutableSortedMap;
import org.gradle.api.internal.tasks.TaskFilePropertySpec;
import org.gradle.internal.fingerprint.CurrentFileCollectionFingerprint;
import org.gradle.internal.fingerprint.FileCollectionFingerprinter;
import org.gradle.internal.fingerprint.FileCollectionFingerprinterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.SortedSet;

public class DefaultTaskFingerprinter implements TaskFingerprinter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTaskFingerprinter.class);

    private final FileCollectionFingerprinterRegistry fingerprinterRegistry;

    public DefaultTaskFingerprinter(FileCollectionFingerprinterRegistry fingerprinterRegistry) {
        this.fingerprinterRegistry = fingerprinterRegistry;
    }

    @Override
    public ImmutableSortedMap<String, CurrentFileCollectionFingerprint> fingerprintTaskFiles(Object owner, SortedSet<? extends TaskFilePropertySpec> fileProperties) {
        ImmutableSortedMap.Builder<String, CurrentFileCollectionFingerprint> builder = ImmutableSortedMap.naturalOrder();
        for (TaskFilePropertySpec propertySpec : fileProperties) {
            CurrentFileCollectionFingerprint result;
            FileCollectionFingerprinter fingerprinter = fingerprinterRegistry.getFingerprinter(propertySpec.getNormalizer());
            LOGGER.debug("Fingerprinting property {} for {}", propertySpec, owner);
            result = fingerprinter.fingerprint(propertySpec.getPropertyFiles());
            builder.put(propertySpec.getPropertyName(), result);
        }
        return builder.build();
    }
}
