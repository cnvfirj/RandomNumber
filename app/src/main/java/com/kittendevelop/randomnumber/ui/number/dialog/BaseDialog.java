package com.kittendevelop.randomnumber.ui.number.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.kittendevelop.randomnumber.R;

public abstract class BaseDialog extends DialogFragment implements DialogFeedback{


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(getContext().getResources().getColor(R.color.transparent,null)));
        return create(inflater);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void exitDialog() {
        dismiss();
    }

    @Override
    public DialogFragment fragment() {
        return this;
    }

    @Override
    public Context context() {
        return getContext();
    }

    protected abstract View create(LayoutInflater inflater);
}
