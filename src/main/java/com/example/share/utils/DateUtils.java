package com.example.share.utils;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateUtils extends DateHelper {

    public static ZoneId zoneId = ZoneId.systemDefault();

    public static LocalDateTime localDateTime = LocalDateTime.now();

    /**
     * 获取当天的00:00:00
     * @return
     */
    public static Date getTodayBeginTime(){
        LocalDateTime todayStart=localDateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);
        ZonedDateTime zdt = todayStart.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    /**
     * 获取当天的23:59:59
     * @return
     */
    public static Date getTodayEndTime(){
        LocalDateTime todayEnd=localDateTime.withHour(23).withMinute(59).withSecond(59).withNano(0);
        ZonedDateTime zdt = todayEnd.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    /**
     * 获取N天前的00:00:00
     * @return
     */
    public static Date getNTodayBeforeBegin(Integer nDays){
        return getNByTypeStart(1,nDays);
    }

    /**
     * 获取N天后的23:59:59
     * @return
     */
    public static Date getNDaysAfterEnd(Integer nDays){
        return getNByTypeEnd(1,nDays);
    }

    /**
     * 获取N天前的当前时间
     * @return
     */
    public static Date getNDaysBeforeNow(Integer nDays){
        return getNByTypeNow(1,nDays,true);
    }

    /**
     * 获取N天后的当前时间
     * @return
     */
    public static Date getNDaysAfterNow(Integer nDays){
        return getNByTypeNow(1,nDays,false);
    }

    /**
     * 获取N月前的00:00:00
     * @return
     */
    public static Date getNMonthsBeforeBegin(Integer nMonths){
        return getNByTypeStart(2,nMonths);
    }

    /**
     * 获取N月后的23:59:59
     * @return
     */
    public static Date getNMonthsAfterEnd(Integer nMonths){
        return getNByTypeEnd(2,nMonths);
    }

    /**
     * 获取N月前的当前时间
     * @return
     */
    public static Date getNMonthsBeforeNow(Integer nMonths){
        return getNByTypeNow(2,nMonths,true);
    }

    /**
     * 获取N月后的当前时间
     * @return
     */
    public static Date getNMonthsAfterNow(Integer nMonths){
        return getNByTypeNow(2,nMonths,false);
    }

    /**
     * 获取N天/月的开始时间
     * @param type 1:day;2:month
     * @param count 具体天数或者月数
     * @return
     */
    public static Date getNByTypeStart(Integer type,Integer count){
        LocalDateTime temp=null;
        if(type.equals(1)){
            temp=localDateTime.plusDays(-count).withHour(0).withMinute(0).withSecond(0).withNano(0);
        }else{
             temp=localDateTime.plusMonths(-count).withHour(0).withMinute(0).withSecond(0).withNano(0);
        }
        ZonedDateTime tempZdt = temp.atZone(zoneId);
        Date date = Date.from(tempZdt.toInstant());
        return date;
    }

    /**
     * 获取N天/月的结束时间
     * @param type 1:day;2:month
     * @param count 具体天数或者月数
     * @return
     */
    public static Date getNByTypeEnd(Integer type,Integer count){
        LocalDateTime temp=null;
        if(type.equals(1)){
            temp=localDateTime.plusDays(count).withHour(23).withMinute(59).withSecond(59).withNano(0);
        }else{
            temp=localDateTime.plusMonths(count).withHour(23).withMinute(59).withSecond(59).withNano(0);
        }
        ZonedDateTime tempZdt = temp.atZone(zoneId);
        Date date = Date.from(tempZdt.toInstant());
        return date;
    }

    /**
     * 获取N天/月的当前
     * @param type 1:day;2:month
     * @param count 具体天数或者月数
     * @param isNegative  前/后
     * @return
     */
    public static Date getNByTypeNow(Integer type,Integer count,boolean isNegative){
        LocalDateTime temp=null;
        if(type.equals(1)){
            if(isNegative){
                temp=localDateTime.plusDays(-count);
            }else{
                temp=localDateTime.plusDays(count);
            }
        }else{
            if(isNegative){
                temp=localDateTime.plusMonths(-count);
            }else{
                temp=localDateTime.plusMonths(count);
            }

        }
        ZonedDateTime tempZdt = temp.atZone(zoneId);
        Date date = Date.from(tempZdt.toInstant());
        return date;
    }


    public static void main(String[] args) {
//        System.out.println(DateHelper.format(getTodayBeginTime(),DateHelper.DATE_TIME_FORMAT));
//        System.out.println(DateHelper.format(getTodayEndTime(),DateHelper.DATE_TIME_FORMAT));
//        System.out.println(DateHelper.format(getNTodayBeforeBegin(7),DateHelper.DATE_TIME_FORMAT));
//        System.out.println(DateHelper.format(getNMonthsBeforeBegin(1),DateHelper.DATE_TIME_FORMAT));
//        System.out.println(DateHelper.format(getNDaysAfterEnd(1),DateHelper.DATE_TIME_FORMAT));
//        System.out.println(DateHelper.format(getNMonthsAfterEnd(1),DateHelper.DATE_TIME_FORMAT));
        System.out.println(DateHelper.format(getNDaysBeforeNow(7),DateHelper.DATE_TIME_FORMAT));
//        System.out.println(DateHelper.format(getNMonthsBeforeNow(1),DateHelper.DATE_TIME_FORMAT));
//        System.out.println(DateHelper.format(getNDaysAfterNow(1),DateHelper.DATE_TIME_FORMAT));
//        System.out.println(DateHelper.format(getNMonthsAfterNow(1),DateHelper.DATE_TIME_FORMAT));
    }

}
