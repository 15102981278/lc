package com.yinhe.dama.entity;

import com.yinhe.dama.aop.PageBean;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName DataRecord
 * @Description 日志
 * @Author lc
 * @Date 2020/3/18 0018 10:03
 * @Version 1.0
 */
@Getter
@Setter
public class DataRecord extends PageBean {
    /**主键*/
    private Long recid;
    /**操作人*/
    private String recname;
    /**操作账号*/
    private String recaccount;
    /**操作模块*/
    private String recmodule;
    /**发起动作*/
    private String recoperation;
    /**操作数据*/
    private String recdata;
    /**操作类*/
    private String reclass;
    /**ip*/
    private String recip;
    /**操作时间*/
    private String rectime;
}
