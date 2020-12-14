package com.kittendevelop.randomnumber.mainDI;

import android.content.SharedPreferences;
import android.content.res.Resources;

public interface CallbackMainAppModule {
    Resources resources();
    SharedPreferences preferences(String TAG);
    SharedPreferences.Editor editor(String TAG);
}
