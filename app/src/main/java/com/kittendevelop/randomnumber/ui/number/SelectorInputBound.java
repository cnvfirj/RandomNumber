package com.kittendevelop.randomnumber.ui.number;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.databinding.ObservableField;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class SelectorInputBound {

    private String mValueFrom;
    private String mValueTo;

    private long mFrom;
    private long mTo;

    private int mColorYes;
    private int mColorNo;
    private boolean mReadiness;

    private final ObservableField<Integer> mColorBackground = new ObservableField<>();

    private final TextWatcher mTextFrom = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if(editable.toString().equals("+"))editable.clear();
            else {
                mValueFrom = editable.toString();
                if (!mValueFrom.equals("") && !mValueFrom.equals("-")) {
                    mFrom = Long.parseLong(mValueFrom);
                } else mFrom = 0;
                switchColorBackground();
            }
        }
    };

    private final TextWatcher mTextTo = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if(editable.toString().equals("+"))editable.clear();
            else {
                mValueTo = editable.toString();
                if (!mValueTo.equals("") && !mValueTo.equals("-")) {
                    mTo = Long.parseLong(mValueTo);
                } else mTo = 0;
                switchColorBackground();
            }

        }
    };


//    @Inject
    public SelectorInputBound() {
        mColorNo = Color.RED;
        mColorYes = Color.GRAY;
        mFrom = 0;
        mTo = 0;
        mValueFrom = "";
        mValueTo = "";
        switchColorBackground();
    }

    public ObservableField<Integer> getColorBackground() {
        return mColorBackground;
    }

    public void applyParams(){
        switchColorBackground();
    }

    public SelectorInputBound setFrom(long mFrom) {
        this.mFrom = mFrom;
            mValueFrom = String.valueOf(mFrom);
        return this;
    }

    public SelectorInputBound setTo(long mTo) {
        this.mTo = mTo;
            mValueTo = String.valueOf(mTo);
        return this;
    }

    public SelectorInputBound setColorYes(int mColorYes) {
        this.mColorYes = mColorYes;
        return this;
    }

    public SelectorInputBound setColorNo(int mColorNo) {
        this.mColorNo = mColorNo;
        return this;
    }

    public boolean isReadiness(){
        return mReadiness;
    }

    public String getValueFrom() {
        return mValueFrom;
    }

    public String getValueTo() {
        return mValueTo;
    }

    public long getFrom() {
        return mFrom;
    }

    public long getTo() {
        return mTo;
    }

    public TextWatcher getWatchFrom() {
        return mTextFrom;
    }

    public TextWatcher getWatchTo() {
        return mTextTo;
    }

    private void switchColorBackground(){
        if(mFrom<mTo&&mValueFrom.length()>0&&mValueTo.length()>0&&!mValueTo.equals("-")&&!mValueFrom.equals("-")){
            mColorBackground.set(mColorYes);
            mReadiness = true;
        }else {
            mColorBackground.set(mColorNo);
            mReadiness = false;
        }
    }
}
