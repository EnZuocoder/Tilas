package com.hfut.tilaswebmangement.Service;

import com.hfut.tilaswebmangement.pojo.ClassOption;
import com.hfut.tilaswebmangement.pojo.PageResult;
import com.hfut.tilaswebmangement.pojo.Student;
import com.hfut.tilaswebmangement.pojo.StudentQueryParam;

import java.util.List;
import java.util.Map;

public interface StudentService {
    PageResult<Student> show(StudentQueryParam param);

    void deleteByIds(Integer[] ids);

    void add(Student student);

    Student getById(Integer id);

    void update(Student student);

    void violation(Integer id, Integer score);
    List<Map<String,Object>> getStudentDegreeData();

    ClassOption getStudentCountData();
}
