package com.yinhe.dama.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName DataPlatformApp
 * @Description 平台App
 * @Author lc
 * @Date 2020/4/7 0007 14:45
 * @Version 1.0
 */
@Setter
@Getter
public class DataPlatformApp {

    //主键
    private Long paid;
    //平台主键
    private Long rp_id;
    //App ID
    private String paapp;
    //App名称
    private String paname;
    //备注  待用
    private String paremark;
    //注册平台
    private DataRegisterPlatform registerPlatform;

}
