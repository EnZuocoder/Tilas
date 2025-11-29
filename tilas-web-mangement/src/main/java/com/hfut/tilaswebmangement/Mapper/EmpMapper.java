package com.hfut.tilaswebmangement.Mapper;
import com.hfut.tilaswebmangement.pojo.Emp;
import com.hfut.tilaswebmangement.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    //分页展示交给了PageHelper实现了把不带分页查询的sql结果放在这
    List<Emp> find(EmpQueryParam empQueryParam);
    //增加员工
    void add(Emp emp);
    //按照id批量删除员工
    void deleteByIds(Integer[]ids);
    //根据id查找员工
    Emp getById(Integer id);
    //更新员工信息
    void update(Emp emp);
    @MapKey("pos")
    List<Map<String, Object>> getJObData();

    List<Emp> getAll();
    @MapKey("name")
    List<Map<String, Object>> getStudentDegreeData();

    //登录时根据用户名和密码查询员工
    Emp getByUsernameAndPassword(Emp emp);
}
