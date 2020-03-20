package com.yinhe.dama.entity;

import com.yinhe.dama.aop.PageBean;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName DataModule
 * @Description 模块信息
 * @Author lc
 * @Date 2020/3/11 0011 13:47
 * @Version 1.0
 */
@Getter
@Setter
public class DataModule extends PageBean {

    /**主键*/
    private Long modid;
    /**模块号*/
    private String module_number;
    /**SIM卡号*/
    private String module_sim;
    /**注册平台ID*/
    private String platform_id;
    /**创建时间*/
    private String register_time;
    /**最近一次上报时间*/
    private String last_time;
    /**是否通过首次测试*/
    private int test_state;
    /**发货地客户*/
    private String module_customer;
    /**添加人*/
    private String username;
    /**备注（待使用）*/
    private String remark;
}
