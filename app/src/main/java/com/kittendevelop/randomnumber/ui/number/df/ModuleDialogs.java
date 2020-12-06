package com.kittendevelop.randomnumber.ui.number.df;


import com.kittendevelop.randomnumber.ui.number.dialog.DialogResult;
import com.kittendevelop.randomnumber.ui.number.dialog.DialogWaiting;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverDialogs;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverResult;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverWaiting;

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
