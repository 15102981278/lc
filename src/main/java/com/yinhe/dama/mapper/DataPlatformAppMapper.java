package com.yinhe.dama.mapper;


import com.yinhe.dama.entity.DataPlatformApp;
import com.yinhe.dama.entity.DataRegisterPlatform;

import java.util.List;

/**
 * @ClassName DataPlatformApp
 * @Description
 * @Author lc
 * @Date 2020-4-7 15:18:55
 * @Version 1.0
 */
public interface DataPlatformAppMapper {

    List<DataPlatformApp> selectPlarformApp();
    List<DataRegisterPlatform> selectPlarform();

}
