package com.kittendevelop.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

import com.kittendevelop.randomnumber.mainDI.MainApplication;
import com.kittendevelop.randomnumber.ui.number.FragmentNumb;
import com.kittendevelop.randomnumber.ui.number.db.DataBaseGeneratedItems;

import javax.inject.Inject;

import static com.kittendevelop.randomnumber.help.Massages.MASSAGE;

public class MainActivity extends AppCompatActivity {


    @Inject
    DataBaseGeneratedItems db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inject();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FragmentNumb.newInstance())
                    .commitNow();
        }

    }

    private void inject(){
        ((MainApplication)getApplication()).component().inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}