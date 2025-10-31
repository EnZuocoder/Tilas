package com.hfut.tilaswebmangement.Service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hfut.tilaswebmangement.Mapper.EmpMapper;
import com.hfut.tilaswebmangement.Service.EmpService;
import com.hfut.tilaswebmangement.pojo.Emp;
import com.hfut.tilaswebmangement.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper mapper;
    public PageResult<Emp> show(Integer page,Integer pageSize)
    {
        //设置请求参数:这里原理属实看不懂
        PageHelper.startPage(page,pageSize);

        // 2. 执行查询 (PageHelper会自动拦截并添加 limit)
        List<Emp> empList = mapper.find();
        Page<Emp> p = (Page<Emp>) empList;

        // 3. 封装 PageResult 对象
        return new PageResult<>(p.getTotal(), p.getResult());
    }
}
