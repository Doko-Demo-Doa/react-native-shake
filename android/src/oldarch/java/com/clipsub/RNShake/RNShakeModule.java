package com.clipsub.RNShake;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = RNShakeModuleImpl.NAME)
public class RNShakeModule extends ReactContextBaseJavaModule {
    private final RNShakeModuleImpl moduleImpl;

    public RNShakeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        moduleImpl = new RNShakeModuleImpl(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return RNShakeModuleImpl.NAME;
    }

    @Override
    public void initialize() {
        super.initialize();
        moduleImpl.onInitialize();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        moduleImpl.onInvalidate();
    }

    @ReactMethod
    public void addListener(String eventName) {
    }

    @ReactMethod
    public void removeListeners(double count) {
    }
}
