package com.hfut.tilaswebmangement.Controller;
import com.hfut.tilaswebmangement.Service.ClassService;
import com.hfut.tilaswebmangement.Service.EmpService;
import com.hfut.tilaswebmangement.pojo.ClassQueryParam;
import com.hfut.tilaswebmangement.pojo.Clazz;
import com.hfut.tilaswebmangement.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clazzs")
@Slf4j
public class ClassController {
    @Autowired
    private ClassService service;
    @GetMapping
    public Result show(ClassQueryParam param) //分页查询
    {
        log.info("班级列表查询:查询信息{}",param);
        return Result.success(service.show(param));
    }
    @DeleteMapping("{id}")
    public Result deleteById( @PathVariable Integer id)
    {
        log.info("删除id为{}的班级信息",id);
        service.DeleteById(id);
        return Result.success();
    }
    @PostMapping
    public Result add(@RequestBody Clazz clazz)
    {
        log.info("添加班级信息:{}",clazz);
        service.add(clazz);
        return Result.success();
    }
    @GetMapping("{id}")
    public Result getById(@PathVariable Integer id)
    {
        log.info("根据id查询班级信息:查询id为{}",id);
        return Result.success(service.getById(id));
    }
    @PutMapping
    public Result update(@RequestBody Clazz clazz)
    {
        log.info("修改班级信息:{}",clazz);
        service.update(clazz);
        return Result.success();
    }
    @GetMapping("/list")
    public Result getAll()
    {
        log.info("查询所有班级信息");
        return Result.success(service.getAll());
    }
}
