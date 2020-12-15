package com.kittendevelop.randomnumber.ui.number.db;

import com.kittendevelop.randomnumber.help.CreateParamsItem;

public abstract class BaseWorkDB {

    protected BaseDao mTable;

    public BaseWorkDB(BaseDao dao) {
        mTable = dao;
    }

    abstract BaseEntity entity(long id);

   abstract boolean clear();

   abstract boolean insert(BaseEntity entity);

   abstract boolean delete(BaseEntity entity);

   void ins(BaseEntity entity){
       mTable.insert(entity);
   }

   void del(BaseEntity entity){
       mTable.delete(entity);
   }

    protected void addParams(BaseEntityItems item){
        int[]params = CreateParamsItem.date();
        long id = 1;
        for (int i:params){
            id*=i;
        }
        item.date(params[0],params[1],params[2])
                .time(params[3],params[4],params[5])
                .id(id);
    }

}
