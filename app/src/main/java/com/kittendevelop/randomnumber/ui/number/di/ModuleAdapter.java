package com.kittendevelop.randomnumber.ui.number.di;

import com.kittendevelop.randomnumber.ui.number.adapters.DiffCallbackNumb;
import com.kittendevelop.randomnumber.ui.number.adapters.EntityItemsAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleAdapter {

    @Provides
    EntityItemsAdapter adapter() {
        return new EntityItemsAdapter(new DiffCallbackNumb());
    }
}
