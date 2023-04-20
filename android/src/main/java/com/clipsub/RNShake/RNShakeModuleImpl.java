package com.clipsub.RNShake;

import android.content.Context;
import android.hardware.SensorManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class RNShakeModuleImpl {
    private final ReactApplicationContext reactContext;

    public static final String NAME = "RNShake";
    public final String EVENT_NAME = "ShakeEvent";

    private CustomShakeDetector mCustomShakeDetector = null;

    public RNShakeModuleImpl(ReactApplicationContext reactContext){
        this.reactContext = reactContext;
    }

    public void onInitialize() {
        mCustomShakeDetector = new CustomShakeDetector(this::sendEvent);

        mCustomShakeDetector.start(
                (SensorManager) reactContext.getSystemService(Context.SENSOR_SERVICE));
    }

    public void onInvalidate() {
        mCustomShakeDetector.stop();
        mCustomShakeDetector = null;
    }

    private void sendEvent(){
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(EVENT_NAME, null);
    }
}
