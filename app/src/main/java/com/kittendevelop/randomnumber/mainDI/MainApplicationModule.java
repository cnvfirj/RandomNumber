package com.kittendevelop.randomnumber.mainDI;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;

import androidx.room.Room;

import com.kittendevelop.randomnumber.ui.number.ModelNumb;
import com.kittendevelop.randomnumber.ui.number.PresenterNumb;
import com.kittendevelop.randomnumber.ui.number.SelectorInputBound;
import com.kittendevelop.randomnumber.ui.number.db.DataBaseGeneratedItems;
import com.kittendevelop.randomnumber.ui.number.work.ThreadRequestResult;
import com.kittendevelop.randomnumber.ui.number.work.ThreadWorkDB;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Module
public class MainApplicationModule implements CallbackMainAppModule{

    private final MainApplication mainApplication;

    public MainApplicationModule(MainApplication application) {
        mainApplication = application;
    }

    @Provides
    @Singleton
    @ForMainApplication
    public Context applicationContext(){
        return mainApplication;
    }


    @Provides
    @Singleton
    public DataBaseGeneratedItems dataBaseGeneratedItems(){
        return Room.databaseBuilder(mainApplication,DataBaseGeneratedItems.class,"numb_data_base")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public PresenterNumb presenterNumb(){
        return new PresenterNumb(new SelectorInputBound(), new ModelNumb(new ThreadRequestResult(),new ThreadWorkDB(dataGeneratedItems())),this);
    }


    @Override
    public Resources resources() {
        return mainApplication.getResources();
    }

    @Override
    public SharedPreferences preferences(String TAG) {
        return mainApplication.getSharedPreferences(TAG,Context.MODE_PRIVATE);
    }


    @Override
    public Editor editor(String TAG) {
        return preferences(TAG).edit();
    }

    @Override
    public DataBaseGeneratedItems dataGeneratedItems() {
        return dataBaseGeneratedItems();
    }

    @Qualifier
    @Retention(RUNTIME)
    public @interface ForMainApplication{

    }

}
