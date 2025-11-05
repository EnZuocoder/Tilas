package com.hfut.tilaswebmangement.Service;

import com.hfut.tilaswebmangement.pojo.Emp;
import com.hfut.tilaswebmangement.pojo.EmpQueryParam;
import com.hfut.tilaswebmangement.pojo.PageResult;

public interface EmpService {
    PageResult<Emp> show(EmpQueryParam empQueryParam);
    void add(Emp emp);
    void deleteByIds(Integer[]ids);

    Emp getById(Integer id);

    void update(Emp emp);
}
