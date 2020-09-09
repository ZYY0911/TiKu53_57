package com.example.tiku53_57.util;

import android.app.AlertDialog;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @LogIn Name win10
 * @Create by 张瀛煜 on 2020/8/10 at 20:27 ：）
 */
public class MyUtils {
    public static final String ROWS = "ROWS_DETAIL";

    public  static  void showDialog(Context context, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(msg);
        builder.setPositiveButton("确定",null);
        builder.create().show();
    }


    public static  String SimpDate(String type, Date date){
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }
}
