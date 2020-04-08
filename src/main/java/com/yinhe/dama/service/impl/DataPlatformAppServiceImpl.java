package com.yinhe.dama.service.impl;

import com.yinhe.dama.entity.DataPlatformApp;
import com.yinhe.dama.entity.DataRegisterPlatform;
import com.yinhe.dama.mapper.DataPlatformAppMapper;
import com.yinhe.dama.service.DataPlatformAppService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DataPlatformAppServiceImpl
 * @Description TODO
 * @Author lc
 * @Date 2020/4/7 0007 16:07
 * @Version 1.0
 */
@Service
@Transactional
public class DataPlatformAppServiceImpl implements DataPlatformAppService {

    @Resource
    DataPlatformAppMapper dataPlatformAppMapper;

    @Override
    public List<DataPlatformApp> selectPlarformApp() {
        return dataPlatformAppMapper.selectPlarformApp();
    }

    @Override
    public  List<DataRegisterPlatform> selectPlarform() {
        return dataPlatformAppMapper.selectPlarform();
    }
}
