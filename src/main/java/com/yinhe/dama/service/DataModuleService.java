package com.yinhe.dama.service;

import com.yinhe.dama.entity.DataModule;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName DataModuleService
 * @Description TODO
 * @Author lc
 * @Date 2020/4/1 0001 9:58
 * @Version 1.0
 */
public interface DataModuleService {

    /**查询模块信息*/
    List<DataModule> selectDamo(DataModule dataModule);
    int selectCont(DataModule dataModule);
    /**扫码添加*/
    int scaddModule(DataModule dataModule, HttpServletRequest request);
}
