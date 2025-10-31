package com.hfut.tilaswebmangement.Mapper;
import com.hfut.tilaswebmangement.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
@Mapper
public interface EmpMapper {
    //分页展现所有员工,按照更新时间倒序排序
    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by update_time desc limit #{index} ,#{pageSize}")
    List<Emp> find(Integer index, Integer pageSize);
    //员工的总记录数
    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id ")
    Long numsOfData();

}
