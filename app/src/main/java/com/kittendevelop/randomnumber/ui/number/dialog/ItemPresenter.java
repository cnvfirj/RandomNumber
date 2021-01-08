package com.kittendevelop.randomnumber.ui.number.dialog;

import javax.inject.Inject;

public class ItemPresenter {

    private ItemModel mModel;



    @Inject
    public ItemPresenter(ItemModel mModel) {
        this.mModel = mModel;
    }
}
