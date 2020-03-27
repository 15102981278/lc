package com.yinhe.dama.aop;

import com.yinhe.dama.entity.DataAccount;
import com.yinhe.dama.entity.DataRecord;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Encapsulation
 * @Description 封装类
 * @Author lc
 * @Date 2020/3/12 0012 14:03
 * @Version 1.0
 */
public class Encapsulation {

    public static String getIp(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }


    public static DataRecord getRec(HttpServletRequest request,String moduname,String reco) {
        DataAccount accu= (DataAccount) request.getSession().getAttribute("acco");
        DataRecord dataRecord = new DataRecord();
        dataRecord.setRecaccount(accu.getAccount());//账号
        dataRecord.setRecip(accu.getIp());//ip
        dataRecord.setRecname(accu.getDataUser().getName());//姓名
        dataRecord.setRecmodule(moduname);//模块名
        dataRecord.setRecoperation(reco);//动作
        dataRecord.setRectime(getTime());//时间
        return dataRecord;
    }


    /**
     * 数据响应
     * @param t
     * @param a
     * @return
     */
    public static JSONObject getJsonObject(List<?> t, int a) {
        JsonConfig config = new JsonConfig();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(t, config);
        jsonObject.put("code", 0);
        jsonObject.put("count", a);
        jsonObject.put("data", jsonArray);
        return jsonObject;
    }

    public static void write(HttpServletResponse response, Object o)throws Exception{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }

    public static JSONObject getJsonObj(int a) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", a);
        return jsonObject;
    }

    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timer  =  sDateFormat.format(date);
        return timer;
    }

    public static int[] getIntArr(String ids) {
        String[] strArray = ids.split(",");
        int[] ints = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            ints[i] = Integer.parseInt(strArray[i]);
        }
        return ints;
    }


    public static boolean  getIs(String sta,HttpServletRequest request) {
        Boolean is = false;
        DataAccount user= (DataAccount) request.getSession().getAttribute("acco");
        if(user.getOperate_authority() != null){
            String operate = user.getOperate_authority();
            String[] ope = operate.split(",");
            for(int i=0; i<ope.length; i++){
                if(sta.equals(ope[i])){
                    is = true;
                }
            }
        }
        return is;
    }

    public static boolean  getIsid(String sta,String aut,HttpServletRequest request) {
        Boolean is = false;
        DataAccount user= (DataAccount) request.getSession().getAttribute("acco");
        if(Long.valueOf(aut) == 0){
            if(user.getAuthority() == 0){
                String operate = user.getOperate_authority();
                String[] ope = operate.split(",");
                for(int i=0; i<ope.length; i++){
                    if(sta.equals(ope[i])){
                        is = true;
                    }
                }
            }
        }else{
            String operate = user.getOperate_authority();
            String[] ope = operate.split(",");
            for(int i=0; i<ope.length; i++){
                if(sta.equals(ope[i])){
                    is = true;
                }
            }
        }

        return is;
    }

    /**导出*/
    public static void dowEx(String exname,HttpServletResponse response, HSSFWorkbook wb) throws UnsupportedEncodingException {
        System.err.println(exname+"....");
        String nowtime = getTime();
        String fileName = exname + nowtime + ".xls";
        fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
        try {
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename="
                    + fileName);// 指定下载的文件名
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(
                    output);
            bufferedOutPut.flush();
            wb.write(bufferedOutPut);
            bufferedOutPut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**权限跳转*/
    public static StringBuffer getStr(String[] a,String[] b) {
        int ishave = 0;
        StringBuffer now = new StringBuffer();
        for(int i=0; i<a.length; i++){
            for(int t=0; t<b.length; t++){
                if(b[t].equals(a[i])){
                    ishave = 1;
                }
            }
            if(ishave == 0){
                now.append(a[i]+",");
            }
            ishave = 0;
        }
        System.err.println("now:   "+now);
        return now;
    }

    /**加密*/
    public static String md5Encode(String inStr) {

        MessageDigest md5;
        StringBuffer hexValue = new StringBuffer();

        try {
            md5 = MessageDigest.getInstance("MD5");

            byte[] byteArray = inStr.getBytes("UTF-8");
            byte[] md5Bytes = md5.digest(byteArray);

            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return hexValue.toString();
    }
}
