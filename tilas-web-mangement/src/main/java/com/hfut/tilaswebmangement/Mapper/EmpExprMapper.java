package com.hfut.tilaswebmangement.Mapper;


import com.hfut.tilaswebmangement.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    void insertBatch(List<EmpExpr> exprList);
}
