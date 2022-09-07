package com.example.android.lifecycles.step1;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;

/**
 * @author Бекзад Насирахунов 15/3/22.
 */
public class ViewModel1 extends ViewModel implements DefaultLifecycleObserver {



    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onStop(owner);
    }
}
