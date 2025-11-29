package com.hfut.tilaswebmangement.Filter;
import com.hfut.tilaswebmangement.utils.CurrentHolder;
import com.hfut.tilaswebmangement.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
//过滤器和Spring没有关系
//过滤器和拦截器同时存在时,先经过过滤器,再经过拦截器
@Slf4j
//@WebFilter("/*")//拦击所有请求
public class TokenFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

            log.info("过滤器启动!");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取到请求路径
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;
        String path=httpServletRequest.getRequestURI();
        //判断是否是登录请求,登录请求直接放行
        if (path.contains("/login")){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        //否则，判断 token是否存在
        else {
            String token=httpServletRequest.getHeader("token");
            //解析token是否存在,
            if (token==null||token.isEmpty()){
                //不存在或者为空串返回错误信息401
                httpServletResponse.setStatus(401);
            }
            else {
                Claims claims=JwtUtils.parseToken(token);
                //存在校验令牌
                if(claims!=null)
                {
                    //校验通过放行
                    Integer empId= (Integer) claims.get("id");
                    CurrentHolder.setCurrentId(empId);
                    log.info("有效token: {}", token);
                    filterChain.doFilter(servletRequest,servletResponse);
                }
                else {
                    //否则,返回错误信息
                    log.info("无效token: {}", token);
                    httpServletResponse.setStatus(401);
                }
            }
        }
    }
    public void destroy() {
        CurrentHolder.remove();//移除线程变量
        log.info("过滤器销毁");
    }
}
