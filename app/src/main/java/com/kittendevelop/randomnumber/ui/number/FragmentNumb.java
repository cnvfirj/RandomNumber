package com.kittendevelop.randomnumber.ui.number;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.databinding.FragmentNumbBinding;
import com.kittendevelop.randomnumber.mainDI.MainApplication;
import com.kittendevelop.randomnumber.ui.number.db.BaseEntityItems;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedEx;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverItem;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverResult;

import javax.inject.Inject;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class FragmentNumb extends Fragment implements FragmentFeedback, ParentFragmentCallback{

    @Inject
    PresenterNumb mPresenter;

    public static FragmentNumb newInstance(){
        return new FragmentNumb();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter.bindView(this);
        return create(inflater);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLists(view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication)getActivity().getApplication()).component().inject(this);
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


    /*этим методом добавляем число в исключение*/
    /*по тэгу определяем откуда пришел результат*/
    /*ручной ввод или поиск*/
    @Override
    public void addToEx(long value, String tag) {
        if(tag.equals(ReceiverResult.TAG))mPresenter.addValueToEX(value, EntityGeneratedEx.SOURCE_AUTO);
    }

    @Override
    public void delete(BaseEntityItems item, String tag) {
        if(tag.equals(ReceiverItem.TAG_STORY))addToEx(item.mNumber, ReceiverResult.TAG);
          else   mPresenter.deleteItem(item, tag);
    }

    @Override
    public void clear(String tag) {
          mPresenter.clearTable(tag);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.end();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showDialog(DialogFragment fragment, String tag) {
            fragment.show(getChildFragmentManager(),tag);
    }

    private void initLists(View v){
        mPresenter.fillLists(v.findViewById(R.id.story_results_numb),v.findViewById(R.id.search_excluded_numb));
    }

    private void saveStateLists(){
        RecyclerView st = getView().findViewById(R.id.story_results_numb);
        st.getLayoutManager().onSaveInstanceState();
        RecyclerView ex = getView().findViewById(R.id.search_excluded_numb);
        ex.getLayoutManager().onSaveInstanceState();
    }
}
