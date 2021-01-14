package com.kittendevelop.randomnumber.ui.number.di;

import com.kittendevelop.randomnumber.ui.number.adapters.AdapterList;
import com.kittendevelop.randomnumber.ui.number.adapters.DiffCallbackNumb;
import com.kittendevelop.randomnumber.ui.number.adapters.DiffUtilIItems;
import com.kittendevelop.randomnumber.ui.number.adapters.EntityItemsAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleAdapter {

//    @Provides
//    EntityItemsAdapter adapter() {
//        return new EntityItemsAdapter(new DiffCallbackNumb());
//    }

    @Provides
    AdapterList adapterList(){
        return new AdapterList(new DiffUtilIItems());
    }
}
