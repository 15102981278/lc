package com.yinhe.dama.entity;

import lombok.Getter;

/**
 * @ClassName KeyWord
 * @Description
 * @Author lc
 * @Date 2020/3/17 0017 9:18
 * @Version 1.0
 */
@Getter
public  class KeyWord {
    //账号
    private  final String acco_sel = "0511";
    private  final String acco_upd = "0512";
    private  final String acco_add = "0513";
    private  final String acco_del = "0514";
    private  final String acco_res = "0515";
    private  final String acco_edi = "0516";
    private  final String acco_exp = "0517";
    //用户
    private  final String user_sel = "0521";
    private  final String user_upd = "0522";
    private  final String user_add = "0523";
    private  final String user_del = "0524";
    //日志
    private  final String reco_sel = "0531";
    private  final String reco_exp = "0532";
    //公司
    private  final String comp_sel = "0541";
    private  final String comp_upd = "0542";
    private  final String comp_add = "0543";
    private  final String comp_del = "0544";
    //模块
    private  final String modu_sel = "0231";
    private  final String modu_upd = "0232";
    private  final String modu_add = "0233";
    private  final String modu_del = "0234";
    private  final String modu_exp = "0235";
    private  final String modu_imp = "0236";
}
