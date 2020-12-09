package com.kittendevelop.randomnumber.ui.number.di;


import com.kittendevelop.randomnumber.ui.number.dialog.DialogResult;
import com.kittendevelop.randomnumber.ui.number.dialog.DialogWaiting;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleDialogs {

    @Provides
    DialogResult dialogRes(){
        return new DialogResult();
    }

    @Provides
    DialogWaiting dialogWait(){
        return new DialogWaiting();
    }

}
