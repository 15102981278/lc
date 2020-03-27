package com.yinhe.dama.aop;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @ClassName SessionAdus
 * @Description TODO
 * @Author lc
 * @Date 2020/3/25 0025 10:05
 * @Version 1.0
 */
public class SessionAdus {

    public static HashMap sessionMap = new HashMap();
    public static HashMap sessionIdmap = new HashMap();
    public static synchronized void AddSession(String key,HttpSession vau) {
            sessionMap.put(key,vau);
    }
    public static synchronized void AddSessionId(HttpSession vau) {
        sessionIdmap.put(vau.getId(),vau);
    }
    public static synchronized void DelSessionId(HttpSession vau) {
        sessionIdmap.remove(vau.getId());
    }
    public static synchronized void DelSession(String key) {
            sessionMap.remove(key);
    }
    public static synchronized HttpSession getSession(String key) {
        return (HttpSession) sessionMap.get(key);
    }
    public static synchronized HashMap getMap() {
        return  sessionMap;
    }

    public static void ifno(String key,HttpSession vau){
        if(null != sessionMap.get(key)){
            sessionMap.remove(key);
            AddSession(key,vau);
            vau.invalidate();
        }else{
            AddSession(key,vau);
        }


    }
}
