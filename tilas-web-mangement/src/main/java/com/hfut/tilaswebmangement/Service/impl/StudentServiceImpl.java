package com.hfut.tilaswebmangement.Service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hfut.tilaswebmangement.Mapper.StudentMapper;
import com.hfut.tilaswebmangement.Service.StudentService;
import com.hfut.tilaswebmangement.pojo.ClassOption;
import com.hfut.tilaswebmangement.pojo.PageResult;
import com.hfut.tilaswebmangement.pojo.Student;
import com.hfut.tilaswebmangement.pojo.StudentQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper mapper;
    @Override
    public PageResult<Student> show(StudentQueryParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Student>list=mapper.find(param);
        Page<Student> page=(Page<Student>)list;
        return new PageResult<>(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        mapper.deleteByIds(ids);
    }

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        mapper.add(student);
    }

    @Override
    public Student getById(Integer id) {
        return mapper.getById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        mapper.update(student);
    }

    @Override
    public void violation(Integer id, Integer score) {
        Student s=mapper.getById(id);
        if(s!=null)
        {
            s.setViolationCount((short) (s.getViolationCount()+1));
            s.setViolationScore((short) (s.getViolationScore()+score));
            s.setUpdateTime(LocalDateTime.now());
            mapper.updateViolation(s);
        }
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return mapper.getStudentDegreeData();
    }

    @Override
    public ClassOption getStudentCountData() {
        List<Map<String ,Object>>list=mapper.getStudentCountData();
        List clazzs=list.stream().map(m->m.get("clazz")).toList();
        List counts=list.stream().map(m->m.get("nums")).toList();
        return new ClassOption(clazzs,counts);
    }
}
