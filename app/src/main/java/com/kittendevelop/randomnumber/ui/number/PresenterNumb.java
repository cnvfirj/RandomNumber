package com.kittendevelop.randomnumber.ui.number;

import android.text.Editable;
import android.text.TextWatcher;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class PresenterNumb {

    private StringBuilder mValueFrom;
    private StringBuilder mValueTo;

    public PresenterNumb() {
        mValueFrom = new StringBuilder();
        mValueTo = new StringBuilder();
    }

    public void setFrom(CharSequence c){
        mValueFrom.append(c);
    }

    public void setTo(CharSequence c){
        mValueTo.append(c);
    }

    public String getValueFrom() {
        return mValueFrom.toString();
    }

    public String getValueTo() {
        return mValueTo.toString();
    }
}
