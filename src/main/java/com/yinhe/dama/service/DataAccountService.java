package com.yinhe.dama.service;

import com.yinhe.dama.entity.DataAccount;
import com.yinhe.dama.entity.DataUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
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
     /**查询有没有用户信息外键 */
     List<DataAccount> selectUid(String ids);
     /**查询用户拥有账号数*/
     int selectAcuid(DataAccount dataAccount);
     /**添加账号*/
     int addAcco(DataAccount dataAccount, HttpServletRequest request);
     /**删除账号*/
     int deleteAcco(String ids,HttpServletRequest request);
     /**修改账号*/
     int updateAccou(DataAccount dataAccount,HttpServletRequest request);
     /**去重*/
     int selectacQc(DataAccount dataUser);
     /**导出*/
     void exportaco(DataAccount dataAccount, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException;
     /**重置密码*/
     int updatePas(DataAccount dataAccount,HttpServletRequest request);
     /**编辑权限*/
     int editAuth(String ids,Long accid,HttpServletRequest request);
}
