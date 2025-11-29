package com.hfut.tilaswebmangement.Config;
//import com.hfut.tilaswebmangement.Interceptor.TokenInterceptor;
import com.hfut.tilaswebmangement.Interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration//配置类
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private TokenInterceptor tokenInterceptor;
    //注册拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        //      /**匹配任意以及多层路径 如/depts/update        /*匹配单层路径 如/depts
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/login");//拦截所有请求,排除登录请求
    }
}
