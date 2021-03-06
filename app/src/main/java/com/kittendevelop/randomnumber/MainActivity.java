package com.kittendevelop.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kittendevelop.randomnumber.mainDI.MainApplication;
import com.kittendevelop.randomnumber.ui.number.FragmentNumb;
import com.kittendevelop.randomnumber.ui.number.db.DataBaseGeneratedItems;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {


    @Inject
    DataBaseGeneratedItems db;

    private FragmentNumb mNumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNumb = FragmentNumb.newInstance();
        setContentView(R.layout.activity_main);
        inject();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, mNumb.activate(true))
                    .commitNow();
        }

    }

    private void inject(){
        ((MainApplication)getApplication()).component().inject(this);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
//    }
}