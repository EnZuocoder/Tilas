package com.hfut.tilaswebmangement.Controller;
import com.hfut.tilaswebmangement.Service.EmpService;
import com.hfut.tilaswebmangement.pojo.Emp;
import com.hfut.tilaswebmangement.pojo.LoginInfo;
import com.hfut.tilaswebmangement.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Slf4j
public class LoginController {
    @Autowired
    private EmpService service;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp)
    {
        log.info("用户登录:登录信息{}",emp);
        //返回登录结果
           LoginInfo info=service.login(emp);
           if(info!=null)
               return Result.success(info);
           else
               return Result.error("用户名或密码错误");
    }
}
