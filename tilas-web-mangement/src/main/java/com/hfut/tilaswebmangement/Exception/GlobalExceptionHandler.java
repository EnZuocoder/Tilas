package com.hfut.tilaswebmangement.Exception;
import com.hfut.tilaswebmangement.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Slf4j
@RestControllerAdvice //全局异常处理器
public class GlobalExceptionHandler {
    @ExceptionHandler
    //捕获大号异常
    public Result ExceptionHandler(Exception e)
    {
        log.error("程序出错了~",e);
        return Result.error("出错啦,请联系管理员");
    }
    @ExceptionHandler
    public Result DuplicateKeyExceptionHandler(DuplicateKeyException e) {
//        Duplicate entry '承玄' for key 'emp.username'
        String msg=e.getMessage();
        log.error("键重复异常:",e);
        //加括号表示捕获组可以用group(1) (2)取出捕获
        Pattern pattern = Pattern.compile("Duplicate entry '(.+)' for key '(.+)'");
        Matcher matcher = pattern.matcher(msg);
        String value="";
        String  key="";
        if(matcher.find())
        {
            value=matcher.group(1);
            key=matcher.group(2);
        }
        HashMap<String,String> map=new HashMap<>();
        map.put("username","用户名");
        map.put("phone","手机号");
        //"emp.username"
        key=key.split("\\.")[1];
        return Result.error(map.get(key)+value+"已存在");
    }
}
