package com.kittendevelop.randomnumber.ui.number;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.databinding.FragmentNumbBinding;
import com.kittendevelop.randomnumber.ui.number.df.DaggerComponentFragmentNumb;

import javax.inject.Inject;


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
        return v;
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public Activity activity() {
        return getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.end();
    }
}
