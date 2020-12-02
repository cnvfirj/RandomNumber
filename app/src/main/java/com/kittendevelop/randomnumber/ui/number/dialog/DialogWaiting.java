package com.kittendevelop.randomnumber.ui.number.dialog;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.kittendevelop.randomnumber.R;

public class DialogWaiting extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*прозрачный фон*/
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(getContext().getResources().getColor(R.color.transparent,null)));
        /*отключение выхода при касании вне диалога*/
        setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        return inflater.inflate(R.layout.waiting,null);
    }

    @Override
    public void onResume() {
        super.onResume();
        /*размер окна*/
        Window window = getDialog().getWindow();
        window.setLayout((int) (getContext().getResources().getDimension(R.dimen.width)), (int) (getContext().getResources().getDimension(R.dimen.width)));
        window.setGravity(Gravity.CENTER);
    }
}
