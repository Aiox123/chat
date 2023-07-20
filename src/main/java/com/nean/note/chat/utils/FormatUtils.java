package com.nean.note.chat.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FormatUtils {

    /*
    * List 集合转为 字符串
    * */
    public static String listToString(List<String> list){
        StringBuilder str = new StringBuilder();
        for (String s : list) {
            str.append(s).append(',');
        }
        String res = str.toString();
        return res.substring(0,res.length() - 1);
    }

    public String getFileFolder(boolean year, boolean month, boolean day){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前日期字符串
        String dateString = sdf.format(new Date());
        // 取出年 月 日
        String[] dateStringArray = dateString.split("-");
        StringBuilder folderString = new StringBuilder();
        if(year){
            folderString.append(dateStringArray[0]);
            folderString.append("/");
        }
        if(month){
            folderString.append(dateStringArray[1]);
            folderString.append("/");
        }
        if(day){
            folderString.append(dateStringArray[2]);
            folderString.append("/");
        }
        return folderString.toString();
    }
}
