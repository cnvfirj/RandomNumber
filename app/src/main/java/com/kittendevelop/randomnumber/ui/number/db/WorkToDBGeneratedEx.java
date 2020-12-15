package com.kittendevelop.randomnumber.ui.number.db;

public class WorkToDBGeneratedEx extends BaseWorkDB{

    public WorkToDBGeneratedEx(BaseDao dao) {
        super(dao);
    }

@Override
boolean clear() {
    boolean b = true;
    for (EntityGeneratedEx item:table().all()){
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
        ins(entity);
        return entity(entity.getId())!=null;
    }

    @Override
    boolean delete(BaseEntity entity) {
        del(entity);
        return entity(entity.getId())==null;
    }

    private DaoGeneratedEx table(){
        return (DaoGeneratedEx)mTable;
    }
}
