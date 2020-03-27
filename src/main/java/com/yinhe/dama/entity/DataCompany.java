package com.yinhe.dama.entity;

import com.yinhe.dama.aop.PageBean;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName DataCompany
 * @Description 公司
 * @Author lc
 * @Date 2020-3-25 16:49:12
 * @Version 1.0
 */
@Getter
@Setter
public class DataCompany extends PageBean {

    /**主键*/
    private Long coid;
    /**公司名*/
    private String coname;
    /**编号*/
    private String conumber;
    /**备注（待用）*/
    private String remark;

}
