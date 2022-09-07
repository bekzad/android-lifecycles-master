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

package com.example.android.lifecycles.step3;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import android.os.SystemClock;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A ViewModel used for the {@link ChronoActivity3}.
 */
public class LiveDataTimerViewModel extends ViewModel implements DefaultLifecycleObserver {

    private static final int ONE_SECOND = 1000;
    private static final String ELAPSED_TIME = "ELAPSED_TIME";

    private MutableLiveData<Long> mElapsedTime = new MutableLiveData<>();

    private SavedStateHandle stateHandle;
    private long mInitialTime;
    private final Timer timer;

    public LiveDataTimerViewModel(SavedStateHandle stateHandle) {
        this.stateHandle = stateHandle;
        Long savedTime = stateHandle.get(ELAPSED_TIME);

        if (savedTime != null) {
            mInitialTime = savedTime;
            final long newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000;

            mElapsedTime.postValue(newValue);
        } else {
            mInitialTime = SystemClock.elapsedRealtime();
        }

        timer = new Timer();

        // Update the elapsed time every second.
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final long newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000;

                // setValue() cannot be called from a background thread so post to main thread.
                mElapsedTime.postValue(newValue);
            }
        }, ONE_SECOND, ONE_SECOND);

    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onStop(owner);
        Long elapsedTime = mElapsedTime.getValue();
        if (elapsedTime != null) {
            stateHandle.set(ELAPSED_TIME, mInitialTime);
        }
    }

    public LiveData<Long> getElapsedTime() {
        return mElapsedTime;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        timer.cancel();
    }
}
