package com.kittendevelop.randomnumber.ui.number.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.databinding.ResultBinding;
import com.kittendevelop.randomnumber.help.ApplyFeedback;
import com.kittendevelop.randomnumber.ui.number.PresenterNumb;
import com.kittendevelop.randomnumber.ui.number.di.DaggerComponentDialogs;
//import com.kittendevelop.randomnumber.ui.number.di.DaggerComponentDialogs;

import javax.inject.Inject;

public class DialogResult extends DialogFragment implements DialogFeedback {

    @Inject
    ResultPresenter mPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(getContext().getResources().getColor(R.color.transparent,null)));
        DaggerComponentDialogs.builder().build().inject(this);
        mPresenter.setResult(getArguments().getString("RESULT_NUMB")).bindView(this);
        return create(inflater);
    }

    private View create(LayoutInflater inflater){
        View v = inflater.inflate(R.layout.result,null);
        ResultBinding binding = ResultBinding.bind(v);
        binding.setPresenter(mPresenter);
        return v;
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void exitDialog() {
        dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }
}
