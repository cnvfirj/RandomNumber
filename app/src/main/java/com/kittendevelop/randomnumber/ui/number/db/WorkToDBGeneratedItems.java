package com.kittendevelop.randomnumber.ui.number.db;

import com.kittendevelop.randomnumber.help.CreateParamsItem;

public class WorkToDBGeneratedItems {

    private DaoGeneratedItem mTable;

    public WorkToDBGeneratedItems(DaoGeneratedItem table) {
        mTable = table;
    }

    public void addItem(String value,long from, long to, int source, ResultWorkDB result){
        EntityGeneratedItem item = new EntityGeneratedItem().confines(from, to).value(value);
        addParams(item);
    }

    private boolean delete(EntityGeneratedItem item){
        mTable.delete(item);
        return mTable.id(item.getId())==null;

    }

    private boolean insert (EntityGeneratedItem item){
        mTable.insert(item);
        return mTable.id(item.getId())!=null;
    }





    private void addParams(EntityGeneratedItem item){
        int[]params = CreateParamsItem.date();
        long id = 1;
        for (int i:params){
            id*=i;
        }
        item.id(id)
                .date(params[0],0,params[1])
                .time(params[2],params[3],params[4]);
    }




}
