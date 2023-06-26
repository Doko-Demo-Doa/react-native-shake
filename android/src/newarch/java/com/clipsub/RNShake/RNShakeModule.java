package com.clipsub.RNShake;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;

public class RNShakeModule extends NativeRNShakeSpec {
    private RNShakeModuleImpl moduleImpl;

    public RNShakeModule(ReactApplicationContext reactContext){
        super(reactContext);
        moduleImpl = new RNShakeModuleImpl(reactContext);
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

    @Override
    public void addListener(String eventName) {

    }

    @Override
    public void removeListeners(double count) {

    }

    @NonNull
    @Override
    public String getName() {
        return RNShakeModuleImpl.NAME;
    }
}