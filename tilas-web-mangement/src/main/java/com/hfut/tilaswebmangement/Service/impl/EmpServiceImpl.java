package com.hfut.tilaswebmangement.Service.impl;
import com.hfut.tilaswebmangement.Mapper.EmpMapper;
import com.hfut.tilaswebmangement.Service.EmpService;
import com.hfut.tilaswebmangement.pojo.Emp;
import com.hfut.tilaswebmangement.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper mapper;
    public PageResult<Emp> show(Integer page,Integer pageSize)
    {
        PageResult<Emp>res=new PageResult<>();
        res.setRows(mapper.find((page-1)*pageSize,pageSize));//起始索引=(页码-1)*每页展现的记录数
        res.setTotal(mapper.numsOfData());
        return res;
    }

}
