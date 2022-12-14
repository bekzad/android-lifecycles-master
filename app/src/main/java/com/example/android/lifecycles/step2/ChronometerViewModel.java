/*
 * Copyright 2019, The Android Open Source Project
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

package com.example.android.lifecycles.step2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

/**
 * A ViewModel used for the {@link ChronoActivity2}.
 */
public class ChronometerViewModel extends AndroidViewModel {

    @Nullable
    private Long mStartTime;

    public ChronometerViewModel(@NonNull Application application) {
        super(application);
    }

    @Nullable
    public Long getStartTime() {
        return mStartTime;
    }

    public void setStartTime(final long startTime) {
        this.mStartTime = startTime;
    }
}
