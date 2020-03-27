package com.yinhe.dama.service;

import com.yinhe.dama.entity.DataCompany;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName DataCompanyService
 * @Description 公司service
 * @Author lc
 * @Date 2020年3月26日11:46:27
 * @Version 1.0
 */
public interface DataCompanyService {

     /**查询*/
     List<DataCompany> selectDaco(DataCompany dataCompany);
     int selectCont(DataCompany dataCompany);
     /**根据id查询*/
     DataCompany selectCoid(Long coid);
     /**查询所有*/
     List<DataCompany> selectcoAll();
     /**去重*/
     int selectcoQc(DataCompany dataCompany);
     /**添加*/
     int addDaco(DataCompany dataCompany, HttpServletRequest request);
     /**删除*/
     int deleteDaco(String ids, HttpServletRequest request);
     /**修改*/
     int updateDaco(DataCompany dataCompany, HttpServletRequest request);
}
