package com.hfut.tilaswebmangement.Service.impl;

import com.hfut.tilaswebmangement.Mapper.deptMapper;
import com.hfut.tilaswebmangement.Service.deptService;
import com.hfut.tilaswebmangement.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class deptServiceimpl implements deptService {
    @Autowired
    private deptMapper mapper;

    public List<Dept> findall() {
        return mapper.findall();
    }

    public void delete(Integer id) {
        mapper.delete(id);
    }

    public void insert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        mapper.insert(dept);
    }
    public Dept getbyid(Integer id)
    {
        return mapper.getbyid(id);
    }
    public void update(Dept dept)
    {
        //补全基础属性:
        dept.setUpdateTime(LocalDateTime.now());
        mapper.update(dept);

    }
}
