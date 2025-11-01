package com.hfut.tilaswebmangement.Service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hfut.tilaswebmangement.Mapper.EmpExprMapper;
import com.hfut.tilaswebmangement.Mapper.EmpMapper;
import com.hfut.tilaswebmangement.Service.EmpService;
import com.hfut.tilaswebmangement.pojo.Emp;
import com.hfut.tilaswebmangement.pojo.EmpExpr;
import com.hfut.tilaswebmangement.pojo.EmpQueryParam;
import com.hfut.tilaswebmangement.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper mapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    public PageResult<Emp> show(EmpQueryParam empQueryParam)
    {
        //设置请求参数:这里原理属实看不懂
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        // 2. 执行查询 (PageHelper会自动拦截并添加 limit)
        List<Emp> empList = mapper.find(empQueryParam);
        Page<Emp> p = (Page<Emp>) empList;

        // 3. 封装 PageResult 对象
        return new PageResult<>(p.getTotal(), p.getResult());
    }
    public void add(Emp emp) {
        //保存员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        emp.setCreateTime(LocalDateTime.now());
        mapper.add(emp);
        //保存员工工作经历信息
        List<EmpExpr>exprList=emp.getExprList() ;
        if(!CollectionUtils.isEmpty(exprList))//判断是否为空
        {
            for(EmpExpr empExpr:exprList)
            {
                //设置员工ID(前端推送不包含id,需要mybatis主键回显)
                empExpr.setEmpId(emp.getId());
            }
            empExprMapper.insertBatch(exprList);
        }


    }
}
