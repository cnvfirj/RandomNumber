package com.kittendevelop.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kittendevelop.randomnumber.ui.number.FragmentNumb;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FragmentNumb.newInstance())
                    .commitNow();
        }
    }


}