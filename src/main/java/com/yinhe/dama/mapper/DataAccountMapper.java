package com.yinhe.dama.mapper;


import com.yinhe.dama.entity.DataAccount;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName DataAccountMapper
 * @Description
 * @Author lc
 * @Date 2020/3/11 0011 13:57
 * @Version 1.0
 */
public interface DataAccountMapper {
    /**登录查询*/
    DataAccount selectDataAccount(DataAccount dataAccount);

    /**查询账号信息*/
    List<DataAccount> selectDaac(DataAccount dataAccount);
    int selectCont(DataAccount dataAccount);
    /**根据id查询账号信息*/
    DataAccount selectAcid(Long valueOf);
    /**登录修改*/
    int upAcc(DataAccount dataAccount);
    /**查询有没有用户信息外键 */
    List<DataAccount> selectUid(int[] ids);
    /**查询用户拥有账号数*/
    int selectAcuid(DataAccount dataAccount);
    /**添加账号*/
    int addAcco(DataAccount dataAccount);
    /**删除账号*/
    int deleteAcco(int[] ids);
    /**修改账号*/
    int updateAccou(DataAccount dataAccount);
    /**去重*/
    int selectacQc(DataAccount dataAccount);
    /**导出日志*/
    List<DataAccount> exportaco(DataAccount dataAccount);
    /**重置密码*/
    int updatePas(DataAccount dataAccount);
    /**编辑权限*/
    int editAuth(DataAccount dataAccount);
    /**修改密码*/
    int updamypass(DataAccount dataAccount);

}
