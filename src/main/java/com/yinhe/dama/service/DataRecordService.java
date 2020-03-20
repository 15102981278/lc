package com.yinhe.dama.service;

import com.yinhe.dama.entity.DataRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @ClassName DataAccountService
 * @Description 日志service
 * @Author lc
 * @Date 2020-3-18 11:41:17
 * @Version 1.0
 */
public interface DataRecordService {

     /**查询日志*/
     List<DataRecord> selectDare(DataRecord dataRecord);
     int selectCont(DataRecord dataRecord);
     /**导出日志*/
     void exportrec(DataRecord dataRecord, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException;
}
