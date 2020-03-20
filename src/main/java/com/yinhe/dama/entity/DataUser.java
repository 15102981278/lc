package com.yinhe.dama.entity;

import com.yinhe.dama.aop.PageBean;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName User
 * @Description 用户信息类
 * @Author lc
 * @Date 2020/3/11 0011 11:47
 * @Version 1.0
 */

@Setter
@Getter
public class DataUser extends PageBean {

    /**主键*/
    private Long uid;
    /**用户姓名*/
    private String name;
    /**用户电话*/
    private String phone;
    /**用户公司*/
    private String company;
    /**备注（待用字段）*/
    private String remark;
    /**创建时间*/
    private String creattime;

}
