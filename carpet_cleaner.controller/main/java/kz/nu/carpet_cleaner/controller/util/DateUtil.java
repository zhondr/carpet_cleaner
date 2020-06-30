package kz.nu.carpet_cleaner.controller.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

  @SuppressWarnings("Duplicates")
  public static Date cutTime(Date d) {
    return toBeginOfDay(d);
  }

  @SuppressWarnings("Duplicates")
  public static Date toBeginOfDay(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }

  public static Date toEndOfDay(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MILLISECOND, 999);
    return cal.getTime();
  }

  public static Date addDays(Date date, int days) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, days);
    return cal.getTime();
  }

  public static Date closestWeekDay(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    if (dayOfWeek == Calendar.SUNDAY) {
      cal.add(Calendar.DATE, 1);
    }
    if (dayOfWeek == Calendar.SATURDAY) {
      cal.add(Calendar.DATE, 2);
    }
    return cal.getTime();
  }

  public static boolean isWeekEnd(Date d) {
    return isSaturday(d) || isSunday(d);
  }

  public static boolean isSunday(Date d) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(d);
    return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
  }

  public static boolean isSaturday(Date d) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(d);
    return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
  }

  public static String convertToStr(Date d) {
    return (d == null) ? null : new SimpleDateFormat("dd.MM.yyyy").format(d);
  }

  public static String convertToStrWithMinutes(Date d) {
    return (d == null) ? null : new SimpleDateFormat("dd.MM.yyyy HH.mm").format(d);
  }

  public static String convertToStrWithSeconds(Date d) {
    return (d == null) ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
  }

  public static boolean equalsDate(Date date1, Date date2) {
    if (date1 == null) {
      return date2 == null;
    }

    return cutTime(date1).compareTo(cutTime(date2)) == 0;
  }

}
