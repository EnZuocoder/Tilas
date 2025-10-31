package com.hfut.tilaswebmangement.Controller;
import com.hfut.tilaswebmangement.Service.EmpService;
import com.hfut.tilaswebmangement.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;
@RestController
@Slf4j
@RequestMapping("/emps")

public class EmpController {
    @Autowired
    private EmpService service;

    static class EmpControllerService {

    };
    //GetMapping后面没写/""说明请求参数是简单参数?xxx=&xxxx=
    @GetMapping
    public Result show(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize)
    {
        log.info("展示第{}页的数据, 每页{}条", page, pageSize);
        return Result.success(service.show(page,pageSize));
    }
}
