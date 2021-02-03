package com.kittendevelop.randomnumber.ui.number;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.kittendevelop.randomnumber.R;
import com.kittendevelop.randomnumber.databinding.FragmentNumbBinding;
import com.kittendevelop.randomnumber.mainDI.MainApplication;
import com.kittendevelop.randomnumber.ui.number.db.BaseEntityItems;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedEx;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverEnterEx;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverInfo;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverItem;
import com.kittendevelop.randomnumber.ui.number.dialog.ReceiverResult;

import javax.inject.Inject;

public class FragmentNumb extends Fragment implements FragmentFeedback, ParentFragmentCallback{

    @Inject
    PresenterNumb mPresenter;

    private boolean mActiveFragment;

    public static FragmentNumb newInstance(){
        return new FragmentNumb();
    }

    public static FragmentNumb newInstance(boolean activate){
        Bundle b = new Bundle();
        b.putBoolean("ACTIVATE",activate);
        return new FragmentNumb();
    }

    public FragmentNumb activate(boolean b){
        Bundle bundle = new Bundle();
        bundle.putBoolean("ACTIVATE",b);
        setArguments(bundle);
        return this;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter.bindView(this);
        Bundle b = getArguments();
        if(b!=null){
            mActiveFragment = b.getBoolean("ACTIVATE");
        }
        return create(inflater);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLists(view);
    }


    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        Bundle b = getArguments();
        if(b!=null)
        getArguments().putBoolean("ACTIVATE",args.getBoolean("ACTIVATE",true));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication)getActivity().getApplication()).component().inject(this);
        setHasOptionsMenu(true);
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
        else if(tag.equals(ReceiverEnterEx.TAG))mPresenter.addValueToEX(value, EntityGeneratedEx.SOURCE_MANUAL);
    }

    @Override
    public void delete(BaseEntityItems item, String tag) {
        if(tag.equals(ReceiverItem.TAG_STORY))addToEx(item.mNumber, ReceiverResult.TAG);
          else   mPresenter.deleteItem(item, tag);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mActiveFragment)
        switch (item.getItemId()){
            case R.id.menu_add_ex:
                showDialog(ReceiverEnterEx.instance().dialog(), "ENTER_EX");
                break;
            case R.id.menu_info:
                showDialog(ReceiverInfo.instance().dialog(),"INFO");
                break;
        }
        return super.onOptionsItemSelected(item);
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
//
//    private void saveStateLists(){
//        RecyclerView st = getView().findViewById(R.id.story_results_numb);
//        st.getLayoutManager().onSaveInstanceState();
//        RecyclerView ex = getView().findViewById(R.id.search_excluded_numb);
//        ex.getLayoutManager().onSaveInstanceState();
//    }
}
