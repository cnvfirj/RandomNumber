package com.kittendevelop.randomnumber.ui.number.dialog;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.kittendevelop.randomnumber.ui.number.db.BaseEntityItems;
import com.kittendevelop.randomnumber.ui.number.db.EntityGeneratedItem;
import com.kittendevelop.randomnumber.ui.number.di.DaggerComponentDialogs;

import javax.inject.Inject;

public class ReceiverItem extends ReceiverDialogs{

    public static String TAG_STORY = "ITEM_STORY";
    public static String TAG_EX = "ITEM_EX";


    private static ReceiverItem single;

    private Bundle mBundle;

    @Inject
    public ReceiverItem(DialogItem mDialog) {
        super(mDialog);
    }

    public static ReceiverItem instance(){
        if(single==null){
            synchronized (ReceiverItem.class){
                single = DaggerComponentDialogs.create().item();
            }
        }
        return single;
    }

    public ReceiverItem item(BaseEntityItems item){
        bundle().putSerializable("ITEM_ITEM",item);
        dialog().setArguments(bundle());
        return this;
    }

    private Bundle bundle(){
        if(mBundle==null)mBundle=new Bundle();
        return mBundle;
    }
}
