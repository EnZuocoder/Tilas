package com.hfut.tilaswebmangement.Mapper;
import com.hfut.tilaswebmangement.pojo.Student;
import com.hfut.tilaswebmangement.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> find(StudentQueryParam param);
    void deleteByIds(Integer[] ids);
    void add(Student student);
    Student getById(Integer id);
    void update(Student student);
    void updateViolation(Student s);
    @MapKey("name")
    List<Map<String, Object>> getStudentDegreeData();
    @MapKey("clazz")
    List<Map<String, Object>> getStudentCountData();
}
