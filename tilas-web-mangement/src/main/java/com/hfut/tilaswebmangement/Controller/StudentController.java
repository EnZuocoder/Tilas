package com.hfut.tilaswebmangement.Controller;
import com.hfut.tilaswebmangement.Service.StudentService;
import com.hfut.tilaswebmangement.pojo.Result;
import com.hfut.tilaswebmangement.pojo.Student;
import com.hfut.tilaswebmangement.pojo.StudentQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/students")
@RestController
@Slf4j
public class StudentController {
    @Autowired
    private StudentService service;
    @GetMapping
    public Result show(StudentQueryParam param)
    {
        log.info("学生列表分页查询");
        return Result.success(service.show(param));
    }
    @DeleteMapping("{ids}")
    public Result deleteByIds( @PathVariable Integer[] ids)
    {
        log.info("删除id为{}的学生信息",ids);
        service.deleteByIds(ids);
        return Result.success();
    }
    @PostMapping
    public Result add(@RequestBody Student student)
    {
        log.info("添加学生信息:{}",student);
        service.add(student);
        return Result.success();
    }
    @GetMapping("{id}")
    public Result getById(@PathVariable Integer id)
    {
        log.info("根据id查询学生信息:查询id为{}",id);
        return Result.success(service.getById(id));
    }
    @PutMapping
    public Result update(@RequestBody Student student)
    {
        log.info("修改学生信息:{}",student);
        service.update(student);
        return Result.success();
    }
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id,@PathVariable Integer score)
    {
        log.info("学生违纪扣分:学生id为{},扣分为{}",id,score);
        service.violation(id,score);
        return Result.success();
    }
}
