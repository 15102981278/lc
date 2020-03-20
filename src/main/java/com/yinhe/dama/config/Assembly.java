package com.yinhe.dama.config;

import com.yinhe.dama.web.tool.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 添加组件
 */
@Controller
public class Assembly {

    /**
     * 扩展springMVC
     * @return
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webmc = new WebMvcConfigurer(){
            /**
             * 页面跳转
             * @param registry
             */
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/data").setViewName("login");
            }

            /**
             * 注册拦截器
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/LoginJumpController/login","/","/archive","/assets/**","/layuiadmin/**");
            }
        };
        return  webmc;
    }

}
