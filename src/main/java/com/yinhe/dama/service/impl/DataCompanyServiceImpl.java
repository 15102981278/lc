package com.yinhe.dama.service.impl;

import com.yinhe.dama.aop.Encapsulation;
import com.yinhe.dama.entity.DataCompany;
import com.yinhe.dama.entity.DataRecord;
import com.yinhe.dama.mapper.DataCompanyMapper;
import com.yinhe.dama.mapper.DataRecordMapper;
import com.yinhe.dama.service.DataCompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName DataCompanyServiceImpl
 * @Description 公司Service实现层
 * @Author lc
 * @Date 2020-3-26 11:47:10
 * @Version 1.0
 */
@Service
@Transactional
public class DataCompanyServiceImpl implements DataCompanyService {

    @Resource
    DataCompanyMapper dataCompanyMapper;
    @Resource
    DataRecordMapper dataRecordMapper;
    private static final String comona= "公司管理";

    /**条件查询*/
    @Override
    public List<DataCompany> selectDaco(DataCompany dataCompany) {
        return dataCompanyMapper.selectDaco(dataCompany);
    }
    @Override
    public int selectCont(DataCompany dataCompany) {
        return dataCompanyMapper.selectCont(dataCompany);
    }
    /**id查询*/
    @Override
    public DataCompany selectCoid(Long coid) {
        return dataCompanyMapper.selectCoid(coid);
    }
    /**查询所有*/
    @Override
    public List<DataCompany> selectcoAll() {
        return dataCompanyMapper.selectcoAll();
    }
    /**添加修改去重*/
    @Override
    public int selectcoQc(DataCompany dataCompany) {
        return dataCompanyMapper.selectcoQc(dataCompany);
    }
    /**添加*/
    @Override
    public int addDaco(DataCompany dataCompany, HttpServletRequest request) {
        int i = dataCompanyMapper.addDaco(dataCompany);
        if(i > 0){
            DataRecord dataRecord= Encapsulation.getRec(request,comona,"添加");
            dataRecordMapper.addRecord(dataRecord);
        }
        return i;
    }
    /**删除*/
    @Override
    public int deleteDaco(String ids, HttpServletRequest request) {
        int[] ints = Encapsulation.getIntArr(ids);
        int i = dataCompanyMapper.deleteDaco(ints);
        if(i > 0){
            DataRecord dataRecord= Encapsulation.getRec(request,comona,"删除");
            dataRecordMapper.addRecord(dataRecord);
        }
        return i;
    }

    /**修改*/
    @Override
    public int updateDaco(DataCompany dataCompany, HttpServletRequest request) {
        int i = dataCompanyMapper.updateDaco(dataCompany);
        if(i > 0){
            DataRecord dataRecord= Encapsulation.getRec(request,comona,"修改");
            dataRecordMapper.addRecord(dataRecord);
        }
        return i;
    }
}
