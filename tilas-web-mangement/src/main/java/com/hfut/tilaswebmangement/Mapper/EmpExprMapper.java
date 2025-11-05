package com.hfut.tilaswebmangement.Mapper;


import com.hfut.tilaswebmangement.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    //多插
    void insertBatch(List<EmpExpr> exprList);
    //多删
    void deleteByEmpIds(Integer[]ids);
    //根据员工id查找工作经历
    List<EmpExpr> getBatchById(Integer id);
}
