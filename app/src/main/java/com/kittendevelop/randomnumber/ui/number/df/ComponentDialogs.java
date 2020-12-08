package com.kittendevelop.randomnumber.ui.number.df;

import com.kittendevelop.randomnumber.ui.number.dialog.DialogResult;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverResult;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverWaiting;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ModuleDialogs.class)
public interface ComponentDialogs {

    ReceiverWaiting waiting();

    ReceiverResult result();
    
    void inject(DialogResult dialog);
}
