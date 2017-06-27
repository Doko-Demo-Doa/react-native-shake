package com.clipsub.RNShake;

import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class RNShakeEventModule extends ReactContextBaseJavaModule {
  private final CustomShakeDetector mShakeDetector;

  public RNShakeEventModule(final ReactApplicationContext reactContext) {
    super(reactContext);
    mShakeDetector = new CustomShakeDetector(new CustomShakeDetector.ShakeListener() {
      @Override
      public void onShake() {
        sendEvent(reactContext, "ShakeEvent", null);
      }
    }, 1);
  }

  @Override
  public String getName() {
    return "RNShakeEvent";
  }

  private void sendEvent(ReactContext reactContext,
                         String eventName,
                         @Nullable WritableMap params) {
    reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
  }
}
