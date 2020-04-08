package com.yinhe.dama.mapper;

import com.yinhe.dama.entity.DataModule;

import java.util.List;

/**
 * @ClassName DataModuleMapper
 * @Description
 * @Author lc
 * @Date 2020-4-1 09:54:34
 * @Version 1.0
 */
public interface DataModuleMapper {

    /**查询模块信息*/
    List<DataModule> selectDamo(DataModule dataModule);
    int selectCont(DataModule dataModule);
    /**扫码添加*/
    int scaddModule(DataModule dataModule);


}
