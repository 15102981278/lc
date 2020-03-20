package com.yinhe.dama.service.impl;

import com.yinhe.dama.aop.Encapsulation;
import com.yinhe.dama.entity.DataAccount;
import com.yinhe.dama.entity.DataRecord;
import com.yinhe.dama.mapper.DataAccountMapper;
import com.yinhe.dama.mapper.DataRecordMapper;
import com.yinhe.dama.service.DataAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName DataUserServiceImpl
 * @Description 账号信息Service实现层
 * @Author lc
 * @Date 2020/3/11 0011 15:05
 * @Version 1.0
 */
@Service
@Transactional
public class DataAccountServiceImpl implements DataAccountService {

    @Resource
    DataAccountMapper accountMapper;
    @Resource
    DataRecordMapper dataRecordMapper;
    private static final String acmona="账号管理";
    /**
     * 登录查询
     * @param dataAccount
     * @return
     */
    @Override
    public DataAccount selectDataAccount(DataAccount dataAccount) {
        return accountMapper.selectDataAccount(dataAccount);
    }
    /**账号查询*/
    @Override
    public List<DataAccount> selectDaac(DataAccount dataAccount) {
        return accountMapper.selectDaac(dataAccount);
    }
    @Override
    public int selectCont(DataAccount dataAccount) {
        return accountMapper.selectCont(dataAccount);
    }
    /**根据ID查询*/
    @Override
    public DataAccount selectAcid(Long valueOf) {
        return accountMapper.selectAcid(valueOf);
    }
    /**登录修改*/
    @Override
    public int upAcc(DataAccount dataAccount,HttpServletRequest request) {
        int i = accountMapper.upAcc(dataAccount);
        if(i > 0){
            DataRecord dataRecord= Encapsulation.getRec(request,"系统登录","系统登录");
            dataRecordMapper.addRecord(dataRecord);
        }
        return  i;
    }









}
