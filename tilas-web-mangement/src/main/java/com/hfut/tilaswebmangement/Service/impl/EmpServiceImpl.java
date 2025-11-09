package com.hfut.tilaswebmangement.Service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hfut.tilaswebmangement.Mapper.EmpExprMapper;
import com.hfut.tilaswebmangement.Mapper.EmpLogMapper;
import com.hfut.tilaswebmangement.Mapper.EmpMapper;
import com.hfut.tilaswebmangement.Service.EmpLogService;
import com.hfut.tilaswebmangement.Service.EmpService;
import com.hfut.tilaswebmangement.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper mapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

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

    //将该方法交给spring事务管理,全部成功提交 commit,否则回滚rollback
    @Transactional(rollbackFor = {Exception.class})    //rollbackfor表示那些异常会回滚
    public void add(Emp emp) {
        try {
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
        //上述代码出现异常也要记录日志
        finally {
            empLogService.insertLog(new EmpLog(null,LocalDateTime.now(),"新增员工信息:"+emp.toString()));
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(Integer[] ids) {
        mapper.deleteByIds(ids);
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Emp getById(Integer id) {
        Emp emp=new Emp();
        emp=mapper.getById(id);//获取到员工基本信息
        List<EmpExpr>exprList=empExprMapper.getBatchById(id);
        emp.setExprList(exprList);
        return emp;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        mapper.update(emp);
        Integer[]ids=new Integer[]{emp.getId()};
        List<EmpExpr>exprList=emp.getExprList();//获取到员工工作经历信息
        if(exprList!=null)
        {
            for(EmpExpr empExpr:exprList)
            {
                empExpr.setEmpId(emp.getId());
            }
            //对员工工作采用先删除再插入
            empExprMapper.deleteByEmpIds(ids);
            empExprMapper.insertBatch(exprList);
        }

    }

    @Override
    public JobOption getEmpJobData() {
       List<Map<String, Object>>list= mapper.getJObData();
       List<Object>jobList=list.stream().map(map-> map.get("pos")).toList();
       List<Object>empCountList=list.stream().map(map-> map.get("nums")).toList();
        return new JobOption(jobList,empCountList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empExprMapper.getEmpGenderData();
    }

    @Override
    public List<Emp> getAll() {

        return mapper.getAll();
    }
}
