package com.kittendevelop.randomnumber.mainDI;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;

import com.kittendevelop.randomnumber.ui.number.db.DataBaseGeneratedItems;

public interface CallbackMainAppModule {
//    Application application();
//    Context context();
    Resources resources();
    SharedPreferences preferences(String TAG);
    SharedPreferences.Editor editor(String TAG);
    DataBaseGeneratedItems dataGeneratedItems();
    ConnectivityManager connection();
}
