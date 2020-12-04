package com.kittendevelop.randomnumber.ui.number;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.databinding.FragmentNumbBinding;
import com.kittendevelop.randomnumber.ui.number.df.DaggerComponentFragmentNumb;

import javax.inject.Inject;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class FragmentNumb extends Fragment implements FragmentFeedback {

    @Inject
    PresenterNumb mPresenter;

    public static FragmentNumb newInstance(){
        return new FragmentNumb();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerComponentFragmentNumb.builder().build().inject(this);

        mPresenter.bindView(this);
        return create(inflater);
    }

    private View create(LayoutInflater inflater){
        View v = inflater.inflate(R.layout.fragment_numb,null);
        FragmentNumbBinding binding = FragmentNumbBinding.bind(v);
        binding.setSelector(mPresenter.getSelector());
        binding.setListener(mPresenter);
        return v;
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.end();
    }

    @Override
    public void showDialog(DialogFragment fragment, String tag) {
            fragment.show(getChildFragmentManager(),tag);
    }
}
