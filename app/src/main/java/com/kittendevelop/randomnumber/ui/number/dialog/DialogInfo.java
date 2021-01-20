package com.kittendevelop.randomnumber.ui.number.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.databinding.InfoBinding;
import com.kittendevelop.randomnumber.ui.number.di.DaggerComponentDialogs;

import javax.inject.Inject;

public class DialogInfo extends BaseDialog{

    @Inject
    InfoPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerComponentDialogs.builder().build().inject(this);
       mPresenter.bindView(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected View create(LayoutInflater inflater) {
        View v = inflater.inflate(R.layout.info,null);
        InfoBinding binding = InfoBinding.bind(v);
        binding.setPresenter(mPresenter);
        return v;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.clear();
    }
}
