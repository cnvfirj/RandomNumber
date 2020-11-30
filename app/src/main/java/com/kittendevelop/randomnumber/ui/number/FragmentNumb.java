package com.kittendevelop.randomnumber.ui.number;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.databinding.FragmentNumbBinding;

import java.io.InputStream;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;


public class FragmentNumb extends Fragment {

    public static FragmentNumb newInstance(){
        return new FragmentNumb();
    }

    private InputFill fill;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fill = new InputFill(getContext().getResources().getColor(R.color.willingness_yes,null),
                getContext().getResources().getColor(R.color.willingness_no,null),
                0,0);
        return create(inflater);
    }

    private View create(LayoutInflater inflater){
        View v = inflater.inflate(R.layout.fragment_numb,null);
        FragmentNumbBinding binding = FragmentNumbBinding.bind(v);
        binding.setFragmentNumb(this);
        binding.setFill(fill);
        return v;
    }

}
