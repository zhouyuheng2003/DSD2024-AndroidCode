package com.example.storesearching.util;
import java.util.Calendar;

public class Time {
    public static String getTime() {
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();

        // 获取年、月、日、时、分、秒
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // 月份从 0 开始，需要加 1
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        // 格式化输出
        String formattedDateTime = String.format("%04d-%02d-%02d %02d:%02d:%02d", year, month, dayOfMonth, hour, minute, second);
        return formattedDateTime;
    }
}

