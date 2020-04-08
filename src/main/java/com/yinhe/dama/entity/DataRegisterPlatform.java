package com.yinhe.dama.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName DataRegisterPlatform
 * @Description 注册平台
 * @Author lc
 * @Date 2020/4/7 0007 14:22
 * @Version 1.0
 */
@Getter
@Setter
public class DataRegisterPlatform {
    //主键
    private Long rpid;
    //平台地址
    private String rpurl;
    //平台名称
    private String rpname;
    //备注 待用
    private String rpremark;
}
