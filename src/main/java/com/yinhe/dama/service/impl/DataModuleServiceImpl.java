package com.yinhe.dama.service.impl;

import com.yinhe.dama.aop.Encapsulation;
import com.yinhe.dama.entity.DataAccount;
import com.yinhe.dama.entity.DataModule;
import com.yinhe.dama.entity.DataRecord;
import com.yinhe.dama.mapper.DataModuleMapper;
import com.yinhe.dama.mapper.DataRecordMapper;
import com.yinhe.dama.service.DataModuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName DataModuleServiceImpl
 * @Description TODO
 * @Author lc
 * @Date 2020/4/1 0001 9:58
 * @Version 1.0
 */
@Service
@Transactional
public class DataModuleServiceImpl implements DataModuleService {

    @Resource
    DataModuleMapper dataModuleMapper;
    @Resource
    DataRecordMapper dataRecordMapper;
    private static final String momona = "模块管理";

    /**条件查询*/
    @Override
    public List<DataModule> selectDamo(DataModule dataModule) {
        return dataModuleMapper.selectDamo(dataModule);
    }
    @Override
    public int selectCont(DataModule dataModule) {
        return dataModuleMapper.selectCont(dataModule);
    }
    /**扫码添加*/
    @Override
    public int scaddModule(DataModule dataModule, HttpServletRequest request) {
        DataAccount dataAccount = (DataAccount) request.getSession().getAttribute("acco");
        dataModule.setUsername(dataAccount.getDataUser().getName());
        dataModule.setRegister_time(Encapsulation.getTime());
        return dataModuleMapper.scaddModule(dataModule);
    }
}
