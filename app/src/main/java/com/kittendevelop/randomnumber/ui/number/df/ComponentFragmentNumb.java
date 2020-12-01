package com.kittendevelop.randomnumber.ui.number.df;


import androidx.fragment.app.Fragment;

import com.kittendevelop.randomnumber.ui.number.FragmentFeedback;
import com.kittendevelop.randomnumber.ui.number.FragmentNumb;
import com.kittendevelop.randomnumber.ui.number.PresenterNumb;
import com.kittendevelop.randomnumber.ui.number.SelectorInputBound;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component
public interface ComponentFragmentNumb {
    void inject(FragmentNumb fragment);
//    PresenterNumb presenter();
}
