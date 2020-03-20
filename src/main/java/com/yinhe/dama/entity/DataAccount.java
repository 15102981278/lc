package com.yinhe.dama.entity;

import com.yinhe.dama.aop.PageBean;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName Account
 * @Description 账号信息
 * @Author lc
 * @Date 2020/3/11 0011 13:33
 * @Version 1.0
 */
@Getter
@Setter
public class DataAccount extends PageBean {

    /**主键*/
    private Long accid;
    /**外键*/
    private Long u_id;
    /**账号*/
    private String account;
    /**密码*/
    private String password;
    /**上次登录时间*/
    private String logon_time;
    /**创建时间*/
    private String establish_time;
    /**权限  超管0，管理员1，操作者2*/
    private Integer authority;
    /**一级权限*/
    private String moduleo_authority;
    /**二级权限*/
    private String modulet_authority;
    /**操作权限*/
    private String operate_authority;
    /**状态*/
    private int state;
    /**ip*/
    private String ip;
    /**备注（待用字段）*/
    private String remark;
    /**用户类*/
    private DataUser dataUser;
    /**条件属性*/
    private String u_name;

}
