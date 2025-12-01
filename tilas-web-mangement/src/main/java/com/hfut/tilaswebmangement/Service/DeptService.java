package com.hfut.tilaswebmangement.Service;


import com.hfut.tilaswebmangement.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findall();
    void delete(Integer id);

    void insert(Dept dept);

    Dept getbyid(Integer id);

    void update(Dept dept);
}
