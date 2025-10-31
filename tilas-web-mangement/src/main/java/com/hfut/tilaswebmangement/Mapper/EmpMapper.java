package com.hfut.tilaswebmangement.Mapper;
import com.hfut.tilaswebmangement.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
@Mapper
public interface EmpMapper {
    //分页展示交给了PageHelper实现了把不带分页查询的sql结果放在这
    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by update_time desc")
    List<Emp> find();
}
