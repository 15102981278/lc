package com.yinhe.dama.service;


import com.yinhe.dama.entity.DataUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName DataUserService
 * @Description 用户service接口层
 * @Author lc
 * @Date 2020/3/11 0011 14:42
 * @Version 1.0
 */
public interface DataUserService {

    /**查询用户信息*/
    List<DataUser> selectDaus(DataUser dataUser);
    int selectCont(DataUser dataUser);
    /**查询所有用户信息*/
    List<DataUser> selectDausAl();
    /**添加*/
    int addDaus(DataUser dataUser, HttpServletRequest request);
    /**删除*/
    int deleteDaus(String ids, HttpServletRequest request);
    /**修改*/
    int updateDaus(DataUser dataUser, HttpServletRequest request);
    /**根据id查询用户信息*/
    DataUser selectUsid(Long uid);
    /**条件导出*/
   /* List<DataUser> expDaus(DataUser dataUser);*/
    /**添加修改去重*/
    int selectQc(DataUser dataUser);
    /**用户表是否已存在公司*/
    List<DataUser> selectCoid(String coid);
}
