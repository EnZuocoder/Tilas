package com.hfut.tilaswebmangement.Service;

import com.hfut.tilaswebmangement.pojo.Emp;
import com.hfut.tilaswebmangement.pojo.PageResult;

public interface EmpService {
    PageResult<Emp> show(Integer page,Integer pageSize);

}
