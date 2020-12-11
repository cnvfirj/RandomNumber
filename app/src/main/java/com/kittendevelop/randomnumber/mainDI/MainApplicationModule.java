package com.kittendevelop.randomnumber.mainDI;

import android.content.Context;

import androidx.room.Room;

import com.kittendevelop.randomnumber.ui.number.db.DataBaseGeneratedItems;

import java.lang.annotation.Retention;
import java.time.format.DateTimeFormatter;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Module
public class MainApplicationModule {

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
        return Room.databaseBuilder(mainApplication,DataBaseGeneratedItems.class,"db__")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Qualifier
    @Retention(RUNTIME)
    public @interface ForMainApplication{

    }
}
