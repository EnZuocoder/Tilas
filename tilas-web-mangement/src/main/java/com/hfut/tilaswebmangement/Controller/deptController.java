package com.hfut.tilaswebmangement.Controller;
import com.hfut.tilaswebmangement.Service.deptService;
import com.hfut.tilaswebmangement.pojo.Dept;
import com.hfut.tilaswebmangement.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Slf4j//该注解提供log对象
@RestController//该注解将方法的返回值作为json数据返回给前端并且表明该类是请求处理类
@RequestMapping("/depts")
public class deptController {
    @Autowired //为接口注入实现类
    private deptService service;
    //处理展示所有部门的请求
    @GetMapping
    public Result list()
    {

     List<Dept>res=service.findall();
     log.info("展示所有部门{}",res);//{}是占位符
        return Result.success(res);
    }
    //删除给定id部门
    @DeleteMapping
    public Result delete(Integer id)//参数名与传递过来的参数名一样就能接收到
    {
        service.delete(id);
        log.info("删除id为{}的部门",id);
        return Result.success();
    }
    @PostMapping
    public Result insert(@RequestBody Dept dept)//@RequestBody注解的作用:将json请求体的数据封装在后面的对象中
            //要求json中的字段名,对象有同名属性
    {
        service.insert(dept);
        log.info("插入了部门{}",dept);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getbyid(@PathVariable Integer id)
    {
        Dept dept=service.getbyid(id);
        log.info("由id{}查询到了部门{}",id,dept);
        return Result.success(dept);
    }
    @PutMapping//更新用put
    public Result updatebyid(@RequestBody Dept dept)
    {
        service.update(dept);
        log.info("更新了部门{}",dept);
        return Result.success();
    }

}
