package com.kittendevelop.randomnumber.ui.number.dialog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.kittendevelop.randomnumber.R;

import javax.inject.Inject;

public class InfoPresenter {

    private InfoModel mModel;

    private DialogFeedback mView;


    @Inject
    public InfoPresenter(InfoModel model) {
        this.mModel = model;
    }

    public void bindView(DialogFeedback view){
        mView = view;
    }

    public String getReport(){
        return mView.context().getResources().getString(mModel.getIdReport(),null);
    }

    @SuppressLint("NonConstantResourceId")
    public void click(View v){
        switch (v.getId()){
            case R.id.info_exit:
                if(mView!=null)mView.exitDialog();
                break;
            case R.id.info_aply:
                if(mView!=null){
                    mView.context().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mModel.getURL())));
                    mView.exitDialog();
                }
                break;
        }
    }

    public void clear(){
        mView = null;
    }
}
