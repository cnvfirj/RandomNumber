package com.kittendevelop.randomnumber.ui.number.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.databinding.ResultBinding;
import com.kittendevelop.randomnumber.ui.number.ParentFragmentCallback;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;
import com.kittendevelop.randomnumber.ui.number.di.DaggerComponentDialogs;

import javax.inject.Inject;

public class DialogResult extends BaseDialog{

    @Inject
    ResultPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerComponentDialogs.builder().build().inject(this);
        mPresenter.setResult((EntityGeneratedItem) getArguments().getSerializable("RESULT_ITEM")).bindView(this);
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    protected View create(LayoutInflater inflater){
        View v = inflater.inflate(R.layout.result,null);
        ResultBinding binding = ResultBinding.bind(v);
        binding.setPresenter(mPresenter);
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

}
