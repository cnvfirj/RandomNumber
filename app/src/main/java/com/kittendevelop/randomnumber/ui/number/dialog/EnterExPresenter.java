package com.kittendevelop.randomnumber.ui.number.dialog;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.ui.number.ParentFragmentCallback;

import javax.inject.Inject;

public class EnterExPresenter {

    private EnterExModel mModel;

    private DialogFeedback mView;

    private final TextWatcher mTextEx = new TextWatcher() {
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
                 mModel.setValue(editable.toString());
            }
        }
    };


    @Inject
    public EnterExPresenter(EnterExModel model) {
        this.mModel = model;
    }

    @SuppressLint("NonConstantResourceId")
    public void click(View v){
        switch (v.getId()){
            case R.id.input_ex_exit:
                if(mView!=null)mView.exitDialog();
                break;
            case R.id.input_ex_apply:
                if(mView!=null) {
                    if(!mModel.getValue().equals("")&&!mModel.getValue().equals("-")) {
                        ParentFragmentCallback mCallback = (ParentFragmentCallback) mView.fragment().getParentFragment();
                        if (mCallback != null)
                            mCallback.addToEx(Long.parseLong(mModel.getValue()), ReceiverEnterEx.TAG);
                    }
                    mView.exitDialog();
                }
                break;
        }
    }

    public void bindView(DialogFeedback view){
        mView = view;
    }

    public TextWatcher getText(){
        return mTextEx;
    }

    public String value(){
        return mModel.getValue();
    }

    public void clear(){
        mView = null;
    }


}
