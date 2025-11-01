package com.hfut.tilaswebmangement.Mapper;
import com.hfut.tilaswebmangement.pojo.Emp;
import com.hfut.tilaswebmangement.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
@Mapper
public interface EmpMapper {
    //分页展示交给了PageHelper实现了把不带分页查询的sql结果放在这
    List<Emp> find(EmpQueryParam empQueryParam);

    void add(Emp emp);
}
