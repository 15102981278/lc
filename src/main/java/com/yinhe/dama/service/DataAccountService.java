package com.yinhe.dama.service;

import com.yinhe.dama.entity.DataAccount;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName DataAccountService
 * @Description 账号service接口层
 * @Author lc
 * @Date 2020-3-12 10:52:09
 * @Version 1.0
 */
public interface DataAccountService {

     /**登录查询*/
     DataAccount selectDataAccount(DataAccount dataAccount);
     /**查询账号信息*/
     List<DataAccount> selectDaac(DataAccount dataAccount);
     int selectCont(DataAccount dataAccount);
     /**根据id查询账号信息*/
     DataAccount selectAcid(Long valueOf);
     /**登录修改*/
     int upAcc(DataAccount dataAccount, HttpServletRequest request);
}
