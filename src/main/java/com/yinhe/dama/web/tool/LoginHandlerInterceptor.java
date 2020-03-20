package com.yinhe.dama.web.tool;

import com.yinhe.dama.entity.DataAccount;
import com.yinhe.dama.entity.DataUser;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *拦截器
 */
@Controller
public class LoginHandlerInterceptor implements HandlerInterceptor{

    /**
     * 预检查
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        DataAccount user= (DataAccount) request.getSession().getAttribute("acco");
        if(user == null){
            System.err.println("false");
            /*request.getRequestDispatcher("/").forward(request,response);*/
            response.sendRedirect("/data");
            return false;
        }else{
            return true;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }


}
