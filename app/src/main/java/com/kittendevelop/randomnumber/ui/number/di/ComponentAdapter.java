package com.kittendevelop.randomnumber.ui.number.di;


import com.kittendevelop.randomnumber.ui.number.adapters.AdapterList;

import dagger.Component;

@Component(modules = ModuleAdapter.class)
public interface ComponentAdapter {
//    EntityItemsAdapter adapter();
    AdapterList adapterItems();
}
