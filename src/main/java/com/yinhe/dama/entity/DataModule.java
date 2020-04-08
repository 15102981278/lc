package com.yinhe.dama.entity;

import com.yinhe.dama.aop.Encapsulation;
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
    /**psk*/
    private String module_psk;
    /**SIM卡号*/
    private String module_sim;
    /**注册平台设备ID*/
    private String platform_id;
    /**创建时间*/
    private String register_time;
    /**最近一次上报时间*/
    private String last_time;
    /**是否通过首次测试 0 未通过/ 1 已通过*/
    private int test_state;
    /**发货地客户*/
    private Long comid;
    /**添加人*/
    private String username;
    /**备注（待使用）*/
    private String remark;
    /**公司*/
    private DataCompany dataCompany;
    /**公司名*/
    private String conam;
    /**结束时间*/
    private String endtime;
}
