package com.yinhe.dama.mapper;


import com.yinhe.dama.entity.DataAccount;

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
    /**修改账号用户外键*/
    int updateUid(int[] ids);

}
