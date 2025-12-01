package com.hfut.tilaswebmangement.Aop;
import com.hfut.tilaswebmangement.Mapper.OperateLogMapper;
import com.hfut.tilaswebmangement.pojo.OperateLog;
import com.hfut.tilaswebmangement.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect //表示该类是一个切面类
@Component
@Slf4j
public class RecordTimeAspect {
    @Autowired
    private OperateLogMapper mapper;
    //切入点表达式 Service包下的所有方法
    //execution(返回值类型 包名.类名.方法名(参数列表))
    // 返回值*表示任意返回值 Service.*表示Service包下任意类任意方法..表示任意参数的方法
//    @Around("execution(* com.hfut.tilaswebmangement.Controller.*.*(..))")
//    public Object recordTime(ProceedingJoinPoint pip) throws Throwable
//    {
//        long begin=System.currentTimeMillis();
//        Object result=pip.proceed();//执行目标方法
//        long end=System.currentTimeMillis();
//        log.info("执行{}方法耗时{}毫秒",pip.getSignature(),end-begin);
//        return result;
//    }
    @Around("@annotation(com.hfut.tilaswebmangement.Annotation.LogInfo)")
    public Object recordOperateLog(ProceedingJoinPoint pip) throws Throwable {

        long begin=System.currentTimeMillis();
       Object result=pip.proceed();//执行目标方法
        long end=System.currentTimeMillis();
        //操作人、操作时间、目标类的全类名、目标方法的方法名、方法运行时参数、返回值、方法执行时长并记录到数据库中去
        OperateLog operateLog=new OperateLog();
        //拿到操作人id
        operateLog.setOperateEmpId(CurrentHolder.getCurrentId());//从线程中拿到jwt令牌中的id
        operateLog.setOperateTime(java.time.LocalDateTime.now());
        operateLog.setClassName(pip.getClass().getName());
        operateLog.setMethodName(pip.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(pip.getArgs()));
        operateLog.setReturnValue(result.toString());
        operateLog.setCostTime(end-begin);
        mapper.insert(operateLog);
        return result;
    }
    //用execution方法切入点表达式表示EmpService的login方法 类似正则
    @Before("execution(* com.hfut.tilaswebmangement.Service.EmpService.login(..))")
    public void demo()
    {
        log.info("这是在EmpService的login方法执行之前执行的代码");

    }
    //基于自定义注解的方法切入点,在目标函数上加上自定义注解,这里添加自定义注解的全类名
    @After("@annotation(com.hfut.tilaswebmangement.Annotation.LogInfo)")//表示切入点为所有被LogInfo注解标注的方法
    public void demo2()
    {
        log.info("这是在被LogInfo注解标注的方法执行之后执行的代码");
    }

}
