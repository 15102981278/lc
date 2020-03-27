package com.yinhe.dama.service.impl;

import com.yinhe.dama.aop.Encapsulation;
import com.yinhe.dama.entity.DataAccount;
import com.yinhe.dama.entity.DataRecord;
import com.yinhe.dama.mapper.DataRecordMapper;
import com.yinhe.dama.service.DataRecordService;
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
 * @Description 用户信息Service实现层
 * @Author lc
 * @Date 2020/3/11 0011 15:05
 * @Version 1.0
 */
@Service
@Transactional
public class DataRecordServiceImpl implements DataRecordService {

    @Resource
    DataRecordMapper dataRecordMapper;
    private static final String remona= "日志管理";
    /**查询*/
    @Override
    public List<DataRecord> selectDare(DataRecord dataRecord) {
        return dataRecordMapper.selectDare(dataRecord);
    }
    @Override
    public int selectCont(DataRecord dataRecord) {
        return dataRecordMapper.selectCont(dataRecord);
    }

    /**导出*/
    @Override
    public void exportrec(DataRecord dataRecord, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {

        //根据条件查询数据库中所有的数据
        List<DataRecord> list = dataRecordMapper.exportrec(dataRecord);
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("日志记录");
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
        cell.setCellValue("操作人");
        cell.setCellStyle(style);
        cell = row.createCell((short)2);
        cell.setCellValue("操作账号");
        cell.setCellStyle(style);
        cell = row.createCell((short)3);
        cell.setCellValue("操作模块");
        cell.setCellStyle(style);
        cell = row.createCell((short)4);
        cell.setCellValue("发起动作");
        cell.setCellStyle(style);
        cell = row.createCell((short)5);
        cell.setCellValue("ip");
        cell.setCellStyle(style);
        cell = row.createCell((short)6);
        cell.setCellValue("操作时间");
        cell.setCellStyle(style);

        for (int i = 0; i < list.size(); i++){
            row = sheet.createRow(i + 1);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue((i+1));
            if(list.get(i).getRecname() == null) {//操作人
                row.createCell((short) 1).setCellValue("");
            }else {
                row.createCell((short) 1).setCellValue(list.get(i).getRecname());
            }
            if(list.get(i).getRecaccount() == null) {//操作账号
                row.createCell((short) 2).setCellValue("");
            }else {
                row.createCell((short) 2).setCellValue(list.get(i).getRecaccount());
            }
            if(list.get(i).getRecmodule() == null) {//操作模块
                row.createCell((short) 3).setCellValue("");
            }else {
                row.createCell((short) 3).setCellValue(list.get(i).getRecmodule());
            }
            if(list.get(i).getRecoperation() == null) {//发起动作
                row.createCell((short) 4).setCellValue("");
            }else{
                row.createCell((short) 4).setCellValue(list.get(i).getRecoperation());
            }
            if(list.get(i).getRecip() == null) {//ip
                row.createCell((short) 5).setCellValue("");
            }else {
                row.createCell((short) 5).setCellValue(list.get(i).getRecip());
            }
            if(list.get(i).getRectime() == null) {//操作时间
                row.createCell((short) 6).setCellValue("");
            }else {
                row.createCell((short) 6).setCellValue(list.get(i).getRectime());
            }
        }
        Encapsulation.dowEx("日志记录",response,wb);

            dataRecord= Encapsulation.getRec(request,remona,"日志导出");
            dataRecordMapper.addRecord(dataRecord);
    }


}
