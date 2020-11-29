package com.kittendevelop.randomnumber.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.databinding.FragmentMainBinding;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;


public class FragmentMain extends Fragment {

    public static FragmentMain newInstance(){
        return new FragmentMain();
    }

    private int value = 10;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return create(inflater);
    }

    private View create(LayoutInflater inflater){
        View v = inflater.inflate(R.layout.fragment_main,null);
        FragmentMainBinding binding = FragmentMainBinding.bind(v);
        binding.setFragmentMain(this);
        return v;
    }

    public TextWatcher getFrom(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                MASSAGE("edit from "+editable.toString());
            }
        };
    }

    public TextWatcher getTo(){
        return new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                MASSAGE("edit to "+editable.toString());
            }
        };
    }

    public String getValue(){
        return ""+value;
    }

    public void setValue(){

    }
}
