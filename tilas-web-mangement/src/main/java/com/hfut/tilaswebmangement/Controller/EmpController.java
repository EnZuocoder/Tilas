package com.hfut.tilaswebmangement.Controller;
import com.hfut.tilaswebmangement.Service.EmpService;
import com.hfut.tilaswebmangement.pojo.Emp;
import com.hfut.tilaswebmangement.pojo.EmpQueryParam;
import com.hfut.tilaswebmangement.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping
    public Result add(@RequestBody Emp emp)
    {
        log.info("新增员工:新增员工信息{}",emp);
        service .add(emp);
        return Result.success();
    }
}
