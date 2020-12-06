package com.kittendevelop.randomnumber.ui.number.df;

import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverResult;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverWaiting;

import dagger.Component;

@Component(modules = ModuleDialogs.class)
public interface ComponentDialogs {

    ReceiverWaiting waiting();

    ReceiverResult result();
}
