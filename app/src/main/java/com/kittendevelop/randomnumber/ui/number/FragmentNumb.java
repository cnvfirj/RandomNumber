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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.databinding.FragmentNumbBinding;
import com.kittendevelop.randomnumber.ui.number.adapters.ExAdapter;
import com.kittendevelop.randomnumber.ui.number.di.DaggerComponentFragmentNumb;
//import com.kittendevelop.randomnumber.ui.number.di.DaggerComponentFragmentNumb;

import javax.inject.Inject;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class FragmentNumb extends Fragment implements FragmentFeedback {

    @Inject
    PresenterNumb mPresenter;

    private RecyclerView mListExceptions;
    private RecyclerView mListStory;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLists(view);
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

    private void initLists(View v){
        mListExceptions = v.findViewById(R.id.search_excluded_numb);
        mListExceptions.setAdapter(new ExAdapter().setColors(new int[]{getContext().getResources().getColor(R.color.willingness_no,null)}));
        mListStory = v.findViewById(R.id.story_results_numb);
        mListStory.setAdapter(new ExAdapter().setColors(new int[]{getContext().getResources().getColor(R.color.willingness_no,null)}));
//        mListExceptions.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

    }
}
