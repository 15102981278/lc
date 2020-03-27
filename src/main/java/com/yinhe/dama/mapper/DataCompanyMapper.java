package com.yinhe.dama.mapper;


import com.yinhe.dama.entity.DataCompany;
import java.util.List;

/**
 * @ClassName DataCompanyMapper
 * @Description 公司
 * @Author lc
 * @Date 2020-3-26 09:32:35
 * @Version 1.0
 */
public interface DataCompanyMapper {
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
    int addDaco(DataCompany dataCompany);
    /**删除*/
    int deleteDaco(int[] ids);
    /**修改*/
    int updateDaco(DataCompany dataCompany);

}
