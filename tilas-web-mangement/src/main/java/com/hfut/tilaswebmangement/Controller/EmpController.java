package com.hfut.tilaswebmangement.Controller;
import com.hfut.tilaswebmangement.Service.EmpService;
import com.hfut.tilaswebmangement.pojo.Emp;
import com.hfut.tilaswebmangement.pojo.EmpQueryParam;
import com.hfut.tilaswebmangement.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.Arrays;

@RestController
@Slf4j
@RequestMapping("/emps")

public class EmpController {
    @Autowired
    private EmpService service;
    //GetMapping后面没写/""说明请求参数是简单参数?xxx=&xxxx=
    @GetMapping
    public Result show(EmpQueryParam empQueryParam)
    {
        log.info("列表查询:查询信息{}",empQueryParam);
        return Result.success(service.show(empQueryParam));
    }
    @GetMapping("{id}")
    public Result getById(@PathVariable Integer id)
    {
        log.info("查询id为{}的员工信息",id);
        Emp emp=service.getById(id);
        return Result.success(emp);
    }

    @PostMapping
    public Result add(@RequestBody Emp emp)
    {
        log.info("新增员工信息{}",emp);
        service .add(emp);
        return Result.success();
    }
    @DeleteMapping
    public Result deleteByIds(Integer[]ids)
    {
        log.info("删除员工{}", Arrays.stream(ids).toArray());
        service.deleteByIds(ids);
        return Result.success();

    }
    @PutMapping
    public Result update(@RequestBody Emp emp)
    {
        log.info("更新了员工信息:{}",emp);
        service.update(emp);
        return Result.success();
    }

}
