package com.kittendevelop.randomnumber.ui.number.di;


import com.kittendevelop.randomnumber.ui.number.dialog.DialogInfo;
import com.kittendevelop.randomnumber.ui.number.dialog.DialogItem;
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

    @Provides
    DialogItem dialogItem(){
        return new DialogItem();
    }


    @Provides
    DialogInfo dialogInfo(){
        return new DialogInfo();
    }

}
