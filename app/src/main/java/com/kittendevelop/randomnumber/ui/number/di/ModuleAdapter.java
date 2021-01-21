package com.kittendevelop.randomnumber.ui.number.di;

import com.kittendevelop.randomnumber.ui.number.adapters.AdapterList;
import com.kittendevelop.randomnumber.ui.number.adapters.DiffUtilIItems;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleAdapter {

    @Provides
    AdapterList adapterList(){
        return new AdapterList(new DiffUtilIItems());
    }
}
