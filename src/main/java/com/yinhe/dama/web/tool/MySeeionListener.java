package com.yinhe.dama.web.tool;

import com.yinhe.dama.aop.SessionAdus;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.util.HashSet;

/**
 * @ClassName MySeeionListener
 * @Description session监听器
 * @Author lc
 * @Date 2020/3/19 0019 11:07
 * @Version 1.0
 */
@Component
public class MySeeionListener implements HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        @SuppressWarnings("unused")
        HttpSession session = httpSessionBindingEvent.getSession();

        System.err.println("key----:" + httpSessionBindingEvent.getName());
        System.err.println("value---:" + httpSessionBindingEvent.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.err.println("--attributeRemoved--");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.err.println("--attributeReplaced--");
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.err.println("--sessionCreated--");
        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        // 在application范围由一个HashSet集保存所有的session
        @SuppressWarnings("unchecked")
        HashSet<HttpSession> sessions = (HashSet<HttpSession>) application.getAttribute("acco");
        if (sessions == null) {
            sessions = new HashSet<HttpSession>();
            application.setAttribute("sessions", sessions);
        }
        // 新创建的session均添加到HashSet集中
        sessions.add(session);
        // 可以在别处从application范围中取出sessions集合
        // 然后使用sessions.size()获取当前活动的session数，即为“在线人数”

        //添加新建的session到MySessionContext中;
        SessionAdus.AddSessionId(event.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) throws ClassCastException {
        System.err.println("--sessionDestroyed--");
        HttpSession session = event.getSession();
        System.err.println("deletedSessionId: " + session.getId());
        System.out.println(session.getCreationTime());
        System.out.println(session.getLastAccessedTime());
        ServletContext application = session.getServletContext();
        HashSet<?> sessions = (HashSet<?>) application.getAttribute("sessions");
        // 销毁的session均从HashSet集中移除
        sessions.remove(session);

        SessionAdus.DelSessionId(session);
    }

}