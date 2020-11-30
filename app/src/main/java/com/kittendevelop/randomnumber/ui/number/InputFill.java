package com.kittendevelop.randomnumber.ui.number;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.databinding.ObservableField;

public class InputFill {

    private String mValueFrom;
    private String mValueTo;

    private int mFrom;
    private int mTo;

    private int mColorYes;
    private int mColorNo;
    private boolean mReadiness;

    private ObservableField<Integer> mColorBackground = new ObservableField<>();

    private TextWatcher mTextFrom = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
             mValueFrom = editable.toString();
             if(!mValueFrom.equals("")){
                 mFrom = Integer.parseInt(mValueFrom);
             }else mFrom = 0;
            switchColorBackground();
        }
    };

    private TextWatcher mTextTo = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            mValueTo = editable.toString();
            if(!mValueTo.equals("")){
                mTo = Integer.parseInt(mValueTo);
            }else mTo = 0;
            switchColorBackground();

        }
    };

    public InputFill() {
        this.mColorBackground.set(Color.GRAY);
        this.mValueFrom = "";
        this.mValueTo = "";
    }

    public InputFill(int colorYes, int colorNo, int valueFrom, int valueTo){
        mColorNo = colorNo;
        mColorYes = colorYes;
        mFrom = valueFrom;
        mTo = valueTo;
        switchColorBackground();
    }

    public ObservableField<Integer> getColorBackground() {
        return mColorBackground;
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

    public int getFrom() {
        return mFrom;
    }

    public int getTo() {
        return mTo;
    }

    public TextWatcher getWatchFrom() {
        return mTextFrom;
    }

    public TextWatcher getWatchTo() {
        return mTextTo;
    }

    private void switchColorBackground(){
        if(mFrom<mTo&&mValueFrom.length()>0&&mValueTo.length()>0){
            mColorBackground.set(mColorYes);
            mReadiness = true;
        }else {
            mColorBackground.set(mColorNo);
            mReadiness = false;
        }
    }
}
