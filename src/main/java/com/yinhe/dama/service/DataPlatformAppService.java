package com.yinhe.dama.service;

import com.yinhe.dama.entity.DataPlatformApp;
import com.yinhe.dama.entity.DataRegisterPlatform;

import java.util.List;

/**
 * @ClassName DataPlatformAppService
 * @Description TODO
 * @Author lc
 * @Date 2020/4/7 0007 16:06
 * @Version 1.0
 */
public interface DataPlatformAppService {

    List<DataPlatformApp> selectPlarformApp();
    List<DataRegisterPlatform>  selectPlarform();
}
