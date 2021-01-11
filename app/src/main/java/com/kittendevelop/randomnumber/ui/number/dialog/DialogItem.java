package com.kittendevelop.randomnumber.ui.number.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.databinding.RedactBinding;
import com.kittendevelop.randomnumber.databinding.ResultBinding;
import com.kittendevelop.randomnumber.ui.number.db.BaseEntityItems;
import com.kittendevelop.randomnumber.ui.number.di.DaggerComponentDialogs;

import javax.inject.Inject;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class DialogItem extends BaseDialog {


    @Inject
    ItemPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerComponentDialogs.builder().build().inject(this);
        mPresenter
                .setItem((BaseEntityItems)getArguments().getSerializable("ITEM_ITEM"),getTag())
                .bindView(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected View create(LayoutInflater inflater) {
        View v = inflater.inflate(R.layout.redact,null);
        RedactBinding binding = RedactBinding.bind(v);
        binding.setPresenter(mPresenter);
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        MASSAGE("resume item "+getTag());
    }

    @Override
    public void onPause() {
        super.onPause();
        MASSAGE("pause item "+getTag());
    }
}
