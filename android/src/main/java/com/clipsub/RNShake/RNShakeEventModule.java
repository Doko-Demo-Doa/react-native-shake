package com.clipsub.RNShake;

import android.support.annotation.Nullable;
import android.content.Context;
import android.hardware.SensorManager;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.bridge.ReactApplicationContext;

import safety.com.br.android_shake_detector.core.ShakeDetector;
import safety.com.br.android_shake_detector.core.ShakeOptions;
import safety.com.br.android_shake_detector.core.ShakeCallback;


@ReactModule(name="RNShakeEvent")
public class RNShakeEventModule extends ReactContextBaseJavaModule {
  private ShakeDetector mShakeDetector;

  public RNShakeEventModule(final ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "RNShakeEvent";
  }

  @ReactMethod
  public void initShake(final ReadableMap props) {
    boolean isBackgroundModeEnable = false;
    if (props.hasKey("background")) {
      try {
        isBackgroundModeEnable = props.getBoolean("background");
      } catch (Exception e) {
      }
    }

    int shakeCount = 2;
    if (props.hasKey("shakeCount")) {
      try {
        shakeCount = props.getInt("shakeCount");
      } catch (Exception e) {
      }
    }

    int shakeInterval = 1000;
    if (props.hasKey("shakeInterval")) {
      try {
        shakeInterval = props.getInt("shakeInterval");
      } catch (Exception e) {
      }
    }

    float sensibility = 2.0f;
    if (props.hasKey("sensibility")) {
      try {
        sensibility = (float) props.getDouble("sensibility");
      } catch (Exception e) {
      }
    }

    ShakeOptions options = new ShakeOptions()
        .background(isBackgroundModeEnable)
        .interval(shakeInterval)
        .shakeCount(shakeCount)
        .sensibility(sensibility);
    if (this.mShakeDetector == null) {
      this.mShakeDetector = new ShakeDetector(options).start(getReactApplicationContext(), new ShakeCallback() {
        @Override
        public void onShake() {
          sendEvent(getReactApplicationContext(), "ShakeEvent", null);
        }
      }); 
    } else {
      mShakeDetector.start(getReactApplicationContext());
    }
  }

  @ReactMethod
  public void removeShake() {
    if (this.mShakeDetector != null) {
      mShakeDetector.stopShakeDetector(getReactApplicationContext());
    }
  }

  private void sendEvent(ReactContext reactContext,
                         String eventName,
                         @Nullable WritableMap params) {
    if (reactContext.hasActiveCatalystInstance()) {
      reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
    }
  }
}
