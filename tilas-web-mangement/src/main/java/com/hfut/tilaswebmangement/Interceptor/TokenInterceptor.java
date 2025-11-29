package com.hfut.tilaswebmangement.Interceptor;
import com.hfut.tilaswebmangement.utils.CurrentHolder;
import com.hfut.tilaswebmangement.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
@Component//交给ioc容器管理
public class TokenInterceptor implements HandlerInterceptor {
    public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //根据jwt令牌验证用户身份
        //首先判断是否为登录请求
            String uri = request.getRequestURI();
            //是否为登录请求已经在WebConfig中排除，这里不需要再判断
            String token=request.getHeader("token");//从请求头中获取jwt令牌
            if(token==null||token.isEmpty()){
                log.info("TokenInterceptor:缺少令牌，拒绝访问");
                response.setStatus(401);//401未授权
                return false;
            }
            else {
                Claims claims=JwtUtils.parseToken(token);
               if(claims==null){
                     log.info("TokenInterceptor:令牌无效或已过期，拒绝访问");
                     response.setStatus(401);//401未授权
                     return false;
               }
               else {
                   //将解析后的jwt令牌中的id字段解析出来并放入ThreadLocal中
                   Integer id=(Integer)claims.get("id");
                   CurrentHolder.setCurrentId(id);
                   return true;
               }
        }
    }
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
            //请求处理完毕后,销毁线程变量ThreadLocal(该线程可能会复用防止出错)
            CurrentHolder.remove();
    }
}
