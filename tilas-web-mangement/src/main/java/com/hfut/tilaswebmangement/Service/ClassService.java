package com.hfut.tilaswebmangement.Service;

import com.hfut.tilaswebmangement.pojo.ClassQueryParam;
import com.hfut.tilaswebmangement.pojo.Clazz;
import com.hfut.tilaswebmangement.pojo.Emp;
import com.hfut.tilaswebmangement.pojo.PageResult;

import java.util.List;

public interface ClassService {
    PageResult<Clazz> show(ClassQueryParam param);

    void DeleteById(Integer id);

    void add(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    List<Clazz> getAll();
}
