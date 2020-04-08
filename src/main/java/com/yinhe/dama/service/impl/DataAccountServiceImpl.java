package com.yinhe.dama.service.impl;

import com.yinhe.dama.aop.Encapsulation;
import com.yinhe.dama.entity.DataAccount;
import com.yinhe.dama.entity.DataRecord;
import com.yinhe.dama.mapper.DataAccountMapper;
import com.yinhe.dama.mapper.DataRecordMapper;
import com.yinhe.dama.service.DataAccountService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
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
    /**查询账号对应用户外键*/
    @Override
    public List<DataAccount> selectUid(String ids) {
        int[] arr = Encapsulation.getIntArr(ids);
        return accountMapper.selectUid(arr);
    }

    /**查询用户账号数*/
    @Override
    public int selectAcuid(DataAccount dataAccount) {
        return accountMapper.selectAcuid(dataAccount);
    }
    /**添加账号*/
    @Override
    public int addAcco(DataAccount dataAccount, HttpServletRequest request) {
        dataAccount.setPassword(Encapsulation.md5Encode("123456"));
        dataAccount.setEstablish_time(Encapsulation.getTime());
        int i = accountMapper.addAcco(dataAccount);
        if(i > 0){
            DataRecord dataRecord= Encapsulation.getRec(request,acmona,"添加");
            dataRecordMapper.addRecord(dataRecord);
        }
        return i;
    }

    /**删除*/
    @Override
    public int deleteAcco(String ids,HttpServletRequest request) {
        int[] ints = Encapsulation.getIntArr(ids);
        int i = accountMapper.deleteAcco(ints);
        if(i > 0){
            DataRecord dataRecord= Encapsulation.getRec(request,acmona,"删除");
            dataRecordMapper.addRecord(dataRecord);
        }
        return i;
    }

    /**修改*/
    @Override
    public int updateAccou(DataAccount dataAccount,HttpServletRequest request) {
        int i = accountMapper.updateAccou(dataAccount);
        if(i > 0){
            DataRecord dataRecord= Encapsulation.getRec(request,acmona,"修改");
            dataRecordMapper.addRecord(dataRecord);
        }
        return i;
    }
    /**去重*/
    @Override
    public int selectacQc(DataAccount dataUser) {
        return accountMapper.selectacQc(dataUser);
    }

    /**导出*/
    @Override
    public void exportaco(DataAccount dataAccount, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        //根据条件查询数据库中所有的数据
        List<DataAccount> list = accountMapper.exportaco(dataAccount);
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("账号记录表");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        sheet.autoSizeColumn((short)0);
        cell.setCellValue("序号");
        cell.setCellStyle(style);
        cell = row.createCell((short)1);
        cell.setCellValue("账号");
        cell.setCellStyle(style);
        cell = row.createCell((short)2);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short)3);
        cell.setCellValue("身份");
        cell.setCellStyle(style);
        cell = row.createCell((short)4);
        cell.setCellValue("注册日期");
        cell.setCellStyle(style);
        cell = row.createCell((short)5);
        cell.setCellValue("登录ip");
        cell.setCellStyle(style);
        cell = row.createCell((short)6);
        cell.setCellValue("登录日期");
        cell.setCellStyle(style);
        cell = row.createCell((short)7);
        cell.setCellValue("备注");
        cell.setCellStyle(style);

        for (int i = 0; i < list.size(); i++){
            row = sheet.createRow(i + 1);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue((i+1));
            if(list.get(i).getAccount() == null) {//账号
                row.createCell((short) 1).setCellValue("");
            }else {
                row.createCell((short) 1).setCellValue(list.get(i).getAccount());
            }
            if(list.get(i).getDataUser().getName() == null) {//姓名
                row.createCell((short) 2).setCellValue("");
            }else {
                row.createCell((short) 2).setCellValue(list.get(i).getDataUser().getName());
            }
            if(list.get(i).getAuthority() == 0) {//身份
                row.createCell((short) 3).setCellValue("超管");
            }else if(list.get(i).getAuthority() == 1){
                row.createCell((short) 3).setCellValue("管理员");
            }else{
                row.createCell((short) 3).setCellValue("操作员");
            }
            if(list.get(i).getEstablish_time() == null) {//注册日期
                row.createCell((short) 4).setCellValue("");
            }else{
                row.createCell((short) 4).setCellValue(list.get(i).getEstablish_time());
            }
            if(list.get(i).getIp() == null) {//登录ip
                row.createCell((short) 5).setCellValue("");
            }else {
                row.createCell((short) 5).setCellValue(list.get(i).getIp());
            }
            if(list.get(i).getLogon_time() == null) {//上次登录时间
                row.createCell((short) 6).setCellValue("");
            }else {
                row.createCell((short) 6).setCellValue(list.get(i).getLogon_time());
            }
            if(list.get(i).getRemark() == null) {//备注
                row.createCell((short) 7).setCellValue("");
            }else {
                row.createCell((short) 7).setCellValue(list.get(i).getRemark());
            }
        }
        Encapsulation.dowEx("账号记录表",response,wb);

        DataRecord dataRecord= Encapsulation.getRec(request,acmona,"账号导出");
        dataRecordMapper.addRecord(dataRecord);
    }
    /**重置密码*/
    @Override
    public int updatePas(DataAccount dataAccount,HttpServletRequest request) {
        dataAccount.setPassword(Encapsulation.md5Encode("123456"));
        int i = accountMapper.updatePas(dataAccount);
        if(i > 0){
            DataRecord dataRecord= Encapsulation.getRec(request,acmona,"重置密码");
            dataRecordMapper.addRecord(dataRecord);
        }
        return i;
    }
    /**编辑权限*/
    @Override
    public int editAuth(String ids,Long accid ,HttpServletRequest request) {
        int i = 0;
        DataAccount dataAccount = new DataAccount();
        dataAccount.setAccid(accid);
        if(ids == null || "".equals(ids)){
            i =accountMapper.editAuth(dataAccount);
        }else{
            String[] strArray = ids.split(",");
            StringBuffer one = new StringBuffer();
            StringBuffer two = new StringBuffer();
            StringBuffer ope = new StringBuffer();
            for(int s =0;s<strArray.length;s++){
                if(strArray[s].length() < 3){
                    one.append(strArray[s]+",");
                }else if(strArray[s].length() < 4){
                    two.append(strArray[s]+",");
                }else if(strArray[s].length() < 5){
                    ope.append(strArray[s]+",");
                }
            }
            dataAccount.setModuleo_authority(one.toString());
            dataAccount.setModulet_authority(two.toString());
            dataAccount.setOperate_authority(ope.toString());
            i = accountMapper.editAuth(dataAccount);
        }
        if(i > 0){
            DataRecord dataRecord= Encapsulation.getRec(request,acmona,"编辑权限");
            dataRecordMapper.addRecord(dataRecord);
        }
        return i;
    }
    /**修改密码*/
    @Override
    public int updamypass(DataAccount dataAccount, HttpServletRequest request) {
       int i = accountMapper.updamypass(dataAccount);
       if(i > 0){
           DataRecord dataRecord= Encapsulation.getRec(request,"设置","修改密码");
           dataRecordMapper.addRecord(dataRecord);
       }
        return i;
    }

}
