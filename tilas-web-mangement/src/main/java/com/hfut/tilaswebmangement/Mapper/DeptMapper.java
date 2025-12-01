package com.hfut.tilaswebmangement.Mapper;

import com.hfut.tilaswebmangement.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;
//数据层只用写接口,mybatis可以提供实现类,将得到的数据自动封装(名字要一样)
@Mapper
public interface DeptMapper {
    @Select("select id, name, create_time, update_time from dept order by update_time desc ")
    List<Dept> findall();
    @Delete("delete  from dept where id=#{id}")
    void delete(Integer id);
    @Insert("insert into dept(name, create_time, update_time) value(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);
    @Select("select id, name, create_time, update_time from dept where id=#{id}")
    Dept getbyid(Integer id);
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
