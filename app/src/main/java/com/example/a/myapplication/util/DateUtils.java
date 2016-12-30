package com.example.a.myapplication.util;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;


public class DateUtils {

//	"created_at": "Wed Jun 17 14:26:24 +0800 2015"

	public static final long ONE_MINUTE_MILLIONS = 60 * 1000;
	public static final long ONE_HOUR_MILLIONS = 60 * ONE_MINUTE_MILLIONS;
	public static final long ONE_DAY_MILLIONS = 24 * ONE_HOUR_MILLIONS;

	public static String getShortTime(String dateStr) {
		String str = "";
		try {
			Date date = new Date(Long.decode(dateStr));
			Date curDate = new Date();

			long durTime = curDate.getTime() - date.getTime();
			int dayStatus = calculateDayStatus(date, curDate);

			if(durTime <= 10 * ONE_MINUTE_MILLIONS) {
				str = "刚刚";
			} else if(durTime < ONE_HOUR_MILLIONS) {
				str = durTime / ONE_MINUTE_MILLIONS + "分钟前";
			} else if(dayStatus == 0) {
				str = durTime / ONE_HOUR_MILLIONS + "小时前";
			} else if(dayStatus == -1) {
				str = "昨天" + DateFormat.format("HH:mm", date);
			} else if(isSameYear(date, curDate) && dayStatus < -1) {
				str = DateFormat.format("MM月dd日", date).toString();
			} else {
				str = DateFormat.format("yyyy年MM月", date).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}

	public static boolean isSameYear(Date targetTime, Date compareTime) {
		Calendar tarCalendar = Calendar.getInstance();
		tarCalendar.setTime(targetTime);
		int tarYear = tarCalendar.get(Calendar.YEAR);

		Calendar compareCalendar = Calendar.getInstance();
		compareCalendar.setTime(compareTime);
		int comYear = compareCalendar.get(Calendar.YEAR);

		return tarYear == comYear;
	}

	public static int calculateDayStatus(Date targetTime, Date compareTime) {
		Calendar tarCalendar = Calendar.getInstance();
		tarCalendar.setTime(targetTime);
		int tarDayOfYear = tarCalendar.get(Calendar.DAY_OF_YEAR);

		Calendar compareCalendar = Calendar.getInstance();
		compareCalendar.setTime(compareTime);
		int comDayOfYear = compareCalendar.get(Calendar.DAY_OF_YEAR);

		return tarDayOfYear - comDayOfYear;
	}
	/**
	 * 通过年份和月份 得到当月的日子
	 */
	public static int getMonthDays(int year, int month) {
		month++;
		switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)){
					return 29;
				}else{
					return 28;
				}
			default:
				return  -1;
		}
	}

	/**
	 * 返回当前月份1号位于周几
	 * @param year
	 * 		年份
	 * @param month
	 * 		月份，传入系统获取的，不需要正常的
	 * @return
	 * 	日：1		一：2		二：3		三：4		四：5		五：6		六：7
	 */
	public static int getFirstDayWeek(int year, int month){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
}
