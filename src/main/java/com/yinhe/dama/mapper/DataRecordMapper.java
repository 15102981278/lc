package com.yinhe.dama.mapper;


import com.yinhe.dama.entity.DataRecord;

import java.util.List;

/**
 * @ClassName DataUserService
 * @Description 日志
 * @Author lc
 * @Date 2020-3-18 10:14:30
 * @Version 1.0
 */
public interface DataRecordMapper {
    /**查询日志*/
    List<DataRecord> selectDare(DataRecord dataRecord);
    int selectCont(DataRecord dataRecord);
    /**添加日志*/
    int addRecord(DataRecord dataRecord);
    /**导出日志*/
    List<DataRecord> exportrec(DataRecord dataRecord);

}
