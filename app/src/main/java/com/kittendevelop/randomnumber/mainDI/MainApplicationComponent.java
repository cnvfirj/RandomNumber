package com.kittendevelop.randomnumber.mainDI;

import com.kittendevelop.randomnumber.MainActivity;
import com.kittendevelop.randomnumber.ui.number.dialog.DialogWaiting;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainApplicationModule.class)
public interface MainApplicationComponent {
    void inject(MainActivity activity);
    void inject(MainApplication application);
    void inject(DialogWaiting dialog);
}
