package com.yinhe.dama.entity;

import com.yinhe.dama.aop.PageBean;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName Authority
 * @Description 总权限
 * @Author lc
 * @Date 2020/3/11 0011 13:40
 * @Version 1.0
 */
@Setter
@Getter
public class DataAuthority extends PageBean {

    /**主键*/
    private Long auid;
    /**标识符*/
    private String identifier;
    /**节点名称*/
    private String node_name;
    /**节点链接*/
    private String url;
    /**父节点标识符*/
    private String parent_node;
    /**备注（待用）*/
    private String remark;
}
