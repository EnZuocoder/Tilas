package com.hfut.tilaswebmangement.Controller;
import com.hfut.tilaswebmangement.Service.EmpService;
import com.hfut.tilaswebmangement.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//统计报表的请求处理类
@RestController
@RequestMapping("/report")
@Slf4j
public class ReportController {
    @Autowired
    private EmpService service;
    @GetMapping("empJobData")
    public Result getEmpJobData()
    {
        log.info("统计员工职位信息:");
        return Result.success(service.getEmpJobData());
    }
    @GetMapping("empGenderData")
    public Result getEmpGenderData()
    {
        log.info("统计员工性别信息:");
        return Result.success(service.getEmpGenderData());

    }

}
