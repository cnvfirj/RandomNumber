package com.kittendevelop.randomnumber.ui.number.di;


import com.kittendevelop.randomnumber.ui.number.FragmentNumb;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component
public interface ComponentFragmentNumb {
    void inject(FragmentNumb fragment);
}
