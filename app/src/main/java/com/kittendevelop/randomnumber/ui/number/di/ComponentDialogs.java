package com.kittendevelop.randomnumber.ui.number.di;

import com.kittendevelop.randomnumber.ui.number.dialog.DialogEnterEx;
import com.kittendevelop.randomnumber.ui.number.dialog.DialogInfo;
import com.kittendevelop.randomnumber.ui.number.dialog.DialogItem;
import com.kittendevelop.randomnumber.ui.number.dialog.DialogResult;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverEnterEx;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverInfo;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverItem;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverResult;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverWaiting;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ModuleDialogs.class)
public interface ComponentDialogs {
    ReceiverWaiting waiting();
    ReceiverResult result();
    ReceiverItem item();
    ReceiverInfo info();
    ReceiverEnterEx enterEx();
    
    void inject(DialogResult dialog);
    void inject(DialogItem dialog);
    void inject(DialogInfo dialog);
    void inject(DialogEnterEx dialog);
}
