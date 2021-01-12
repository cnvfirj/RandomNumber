package com.kittendevelop.randomnumber.ui.number;

import com.kittendevelop.randomnumber.ui.number.db.BaseEntityItems;

public interface ParentFragmentCallback {

    public void addToEx(long value, String tag);
    public void delete(BaseEntityItems item, String tag);
    public void clear(String tag);
}
