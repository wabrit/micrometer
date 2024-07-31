/*
 * Copyright 2024 VMware, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micrometer.observation.tck;

import io.micrometer.observation.Observation;
import io.micrometer.observation.Observation.Context;

/**
 * A {@link RuntimeException} that can be thrown when an invalid {@link Observation}
 * detected.
 *
 * @author Jonatan Ivanov
 * @since 1.14.0
 */
public class InvalidObservationException extends RuntimeException {

    private final Context context;

    InvalidObservationException(String message, Context context) {
        super(message);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

}