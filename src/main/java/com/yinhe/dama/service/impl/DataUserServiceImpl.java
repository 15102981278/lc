package com.yinhe.dama.service.impl;

import com.yinhe.dama.aop.Encapsulation;
import com.yinhe.dama.entity.DataRecord;
import com.yinhe.dama.entity.DataUser;
import com.yinhe.dama.mapper.DataRecordMapper;
import com.yinhe.dama.mapper.DataUserMapper;
import com.yinhe.dama.service.DataUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName DataUserServiceImpl
 * @Description 用户信息Service实现层
 * @Author lc
 * @Date 2020/3/11 0011 15:05
 * @Version 1.0
 */
@Service
@Transactional
public class DataUserServiceImpl implements DataUserService {

    @Resource
    DataUserMapper dataUserMapper;
    @Resource
    DataRecordMapper dataRecordMapper;
    private static final String usmona = "用户管理";
    /**用户查询*/
    @Override
    public List<DataUser> selectDaus(DataUser dataUser) {
        return dataUserMapper.selectDaus(dataUser);
    }
    @Override
    public int selectCont(DataUser dataUser) {
        return dataUserMapper.selectCont(dataUser);
    }
    /**查询所有用户*/
    @Override
    public List<DataUser> selectDausAl() {
        return dataUserMapper.selectDausAl();
    }

    /**用户添加*/
    @Override
    public int addDaus(DataUser dataUser, HttpServletRequest request) {

         int i = dataUserMapper.addDaus(dataUser);
         if(i > 0){
             DataRecord dataRecord= Encapsulation.getRec(request,usmona,"添加");
             dataRecordMapper.addRecord(dataRecord);
         }
        return i;
    }
    /**用户删除*/
    @Override
    public int deleteDaus(String ids,HttpServletRequest request) {

        int[] ints = Encapsulation.getIntArr(ids);
        int i = dataUserMapper.deleteDaus(ints);
        if(i > 0){
         /*   dataAccountMapper.updateUid(ints);*/
            DataRecord dataRecord= Encapsulation.getRec(request,usmona,"删除");
            dataRecordMapper.addRecord(dataRecord);
        }
        return i;
    }
    /**用户修改*/
    @Override
    public int updateDaus(DataUser dataUser,HttpServletRequest request) {

        int i = dataUserMapper.updateDaus(dataUser);
        if(i > 0){
            DataRecord dataRecord= Encapsulation.getRec(request,usmona,"修改");
            dataRecordMapper.addRecord(dataRecord);
        }
        return i;
    }
    /**根据id查询用户信息*/
    @Override
    public DataUser selectUsid(Long uid) {
        return dataUserMapper.selectUsid(uid);
    }
   /* *导出*//*
    @Override
    public List<DataUser> expDaus(DataUser dataUser) {
        return dataUserMapper.expDaus(dataUser);
    }*/
    /**添加修改去重*/
    @Override
    public int selectQc(DataUser dataUser) {
        return dataUserMapper.selectQc(dataUser);
    }
    /**查看公司是否存在*/
    @Override
    public List<DataUser> selectCoid(String coid) {
        int [] ids = Encapsulation.getIntArr(coid);
        return dataUserMapper.selectCoid(ids);
    }
}
