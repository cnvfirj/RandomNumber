package com.kittendevelop.randomnumber.ui;

import android.text.Editable;
import android.text.TextWatcher;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class PresenterMain {

    private StringBuilder mValueFrom;
    private StringBuilder mValueTo;

    public PresenterMain() {
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
