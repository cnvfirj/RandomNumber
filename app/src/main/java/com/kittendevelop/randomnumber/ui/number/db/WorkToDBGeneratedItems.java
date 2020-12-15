package com.kittendevelop.randomnumber.ui.number.db;

import com.kittendevelop.randomnumber.help.CreateParamsItem;

public class WorkToDBGeneratedItems extends BaseWorkDB {

    public WorkToDBGeneratedItems(BaseDao table) {
        super(table);
    }

    @Override
    boolean clear() {
        boolean b = true;
        for (EntityGeneratedItem item:table().all()){
            if(!delete(item))b = false;
        }
        return b;
    }

    @Override
    BaseEntity entity(long id) {
        return table().id(id);
    }

    @Override
    boolean insert(BaseEntity entity) {
        del(entity);
        return entity(entity.getId())==null;
    }

    @Override
    boolean delete(BaseEntity entity) {
        del(entity);
        return entity(entity.getId())!=null;
    }


    private DaoGeneratedItem table(){
        return (DaoGeneratedItem)mTable;
    }

}
