package com.clipsub.RNShake;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.TurboReactPackage;

import java.util.HashMap;
import java.util.Map;

public class RNShakePackage extends TurboReactPackage {

    @Nullable
    @Override
    public NativeModule getModule(String name, ReactApplicationContext reactContext) {
        if(name.equals(RNShakeModuleImpl.NAME)){
            return new RNShakeModule(reactContext);
        } else {
            return null;
        }
    }

    @Override
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return () -> {
            boolean isTurboModule = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
            final Map<String, ReactModuleInfo> moduleInfos = new HashMap<>();
            moduleInfos.put(
                    RNShakeModuleImpl.NAME,
                    new ReactModuleInfo(
                            RNShakeModuleImpl.NAME,
                            RNShakeModuleImpl.NAME,
                            false,
                            false,
                            true,
                            false,
                            isTurboModule
                    )
            );
            return moduleInfos;
        };
    }
}