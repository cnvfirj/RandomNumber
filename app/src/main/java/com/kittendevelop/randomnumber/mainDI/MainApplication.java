package com.kittendevelop.randomnumber.mainDI;

import android.app.Application;

public class MainApplication extends Application {

    private MainApplicationComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainApplicationComponent.builder()
                .mainApplicationModule(new MainApplicationModule(this))
                .build();
        mainComponent.inject(this);
    }

    public MainApplicationComponent component(){
        return mainComponent;
    }

}
