package com.kittendevelop.randomnumber.ui.number.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.databinding.ResultBinding;

import javax.inject.Inject;

public class DialogItem extends BaseDialog {


    @Inject
    ItemPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    protected View create(LayoutInflater inflater) {
        View v = inflater.inflate(R.layout.result,null);
        return v;
    }
}
