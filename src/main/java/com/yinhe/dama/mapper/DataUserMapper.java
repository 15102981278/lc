package com.yinhe.dama.mapper;

import com.yinhe.dama.entity.DataUser;

import java.util.List;

/**
 * @ClassName DataAccountMapper
 * @Description 用户信息Dao层
 * @Author lc
 * @Date 2020/3/11 0011 13:57
 * @Version 1.0
 */
public interface DataUserMapper {

    /**查询用户信息*/
    List<DataUser> selectDaus(DataUser dataUser);
    int selectCont(DataUser dataUser);

    /**添加*/
    int addDaus(DataUser dataUser);
    /**删除*/
    int deleteDaus(int[] ids);
    /**修改*/
    int updateDaus(DataUser dataUser);
    /**根据id查询用户信息*/
    DataUser selectUsid(Long uid);
    /**条件导出*/
    List<DataUser> expDaus(DataUser dataUser);

}
