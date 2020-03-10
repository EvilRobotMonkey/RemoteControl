package com.orbyun.utils;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by 吴春娜 on 2017/12/19.
 */

public class FloatUtil {
    private FloatUtil(){
        throw new Error("工具类FloatUtil不可实例化");
    }

    /**
     * 格式化数据返回 小数点后四位表示
     * */
    public static String toFourDianString(Float floatValue) {
        if (floatValue == null) {
            return "--";
        }
        DecimalFormat format = new DecimalFormat("#0.0000");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(floatValue);
    }

    /**
     * 格式化数据返回 小数点后两位表示
     * */
    public static String toTwoDianString(Float floatValue) {
        if (floatValue == null) {
            return "--";
        }
        DecimalFormat format = new DecimalFormat("#0.00");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(floatValue);
    }

    /**
     * 格式化数据返回 小数点后两位表示
     * */
    public static String toTwoDianStringSeparator(Float floatValue) {
        if (floatValue == null) {
            return "--";        }
        DecimalFormat format = new DecimalFormat("#,##0.00");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(floatValue);
    }

    /**
     * 格式化数据返回 小数点后两位表示
     * */
    public static String toStringSeparator(Integer intValue) {
        if (intValue == null) {
            return "--";        }
        DecimalFormat format = new DecimalFormat("#,##0");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(intValue);
    }

    public static String toStringSeparator(Long longValue) {
        if (longValue == null) {
            return "--";        }
        DecimalFormat format = new DecimalFormat("#,##0");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(longValue);
    }

    /**
     * 格式化数据返回 小数点后四位表示
     * */
    public static String toFourDianString(Double doubleValue) {
        if (doubleValue == null) {
            return "--";        }
        DecimalFormat format = new DecimalFormat("#0.0000");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(doubleValue);
    }

    /**
     * 格式化数据返回 小数点后两位表示
     * */
    public static String toTwoDianString(Double doubleValue) {
        if (doubleValue == null) {
            return "--";        }
        DecimalFormat format = new DecimalFormat("###0.00");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(doubleValue);
    }

    /**
     * 格式化数据返回 小数点后两位表示
     * */
    public static String toZeroDianStringSeparator(Double doubleValue) {
        if (doubleValue == null) {
            return "--";        }
        DecimalFormat format = new DecimalFormat("#,##0.##");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(doubleValue);
    }

    /**
     * 格式化数据返回 小数点后两位表示
     * */
    public static String toTwoDianStringSeparator(Double doubleValue) {
        if (doubleValue == null) {
            return "--";        }
        DecimalFormat format = new DecimalFormat("#,##0.00");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(doubleValue);
    }

    public static String yesterDayProfit(Double doubleValue){
        if(doubleValue==null){
            return "--";
        }
        DecimalFormat format = new DecimalFormat("0");
        if(doubleValue < 9999.99){
            return toTwoDianString(doubleValue);
        }else if(doubleValue <= 999999){
            return format.format(doubleValue);
        }else if(doubleValue <= 9999999){
            return toTwoDianString(doubleValue/10000) + "万";
        }else{
            return format.format(doubleValue/10000) + "万";
        }
    }

    /**
     * 去掉多余小数位
     * */
    public static String toString(Double doubleValue) {
        if (doubleValue == null) {
            return "--";        }
        DecimalFormat format = new DecimalFormat("0.##");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(doubleValue);
    }

    /**
     * 去掉多余小数位
     * */
    public static String toStringSeparator(Double doubleValue) {
        if (doubleValue == null) {
            return "--";        }
        DecimalFormat format = new DecimalFormat("#,##0");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(doubleValue);
    }

    /**
     * 格式化数据返回四舍五入两位表示  BigDecimal
     * */
//    public static String toTwoDianString(BigDecimal bigDecimal) {
//        if (bigDecimal == null) {
//            return MyApplication.applicationContext.getResources().getString(R.string.default_value);
//        }
//        DecimalFormat df = new DecimalFormat();
//        df.applyPattern(",##0.00");
//        return df.format(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP));
//    }

    /**
     * 格式化数据返回百分比 小数点后两位表示
     * */
    public static String toPercentage(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return "--";        }
        DecimalFormat format = new DecimalFormat("0.00%");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(bigDecimal);
    }
    /**
     * 格式化数据返回百分比 小数点后两位表示
     * */
    public static String toPercentage(Double value) {
        if (value == null) {
            return "--";        }
        DecimalFormat format = new DecimalFormat("0.####%");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(value);
    }

    /**
     * 格式化数据返回 小数点后四位表示
     */
    public static String toFourDianStringSeparator(Double doubleValue) {
        if (doubleValue == null) {
            return "--";        }
        DecimalFormat format = new DecimalFormat("#,##0.####");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(doubleValue);
    }

    /**
     * 格式化数据返回 小数点后三位表示
     */
    public static String toThreeDianStringSeparator(BigDecimal doubleValue) {
        if (doubleValue == null) {
            return "--";        }
        DecimalFormat format = new DecimalFormat("#,##0.###");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(doubleValue);
    }

    /**
     * 去掉多余小数位
     */
    public static String toStringThree(Double doubleValue) {
        if (doubleValue == null) {
            return "--";        }
        DecimalFormat format = new DecimalFormat("0.###");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(doubleValue);
    }

    /**
     * 格式化数据返回 小数点后两位表示
     */
    public static String toTwoDianStringSeparatorNoPoint(BigDecimal doubleValue) {
        if (doubleValue == null) {
            return "--";        }
        DecimalFormat format = new DecimalFormat("#,##0");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(doubleValue);
    }

    /**
     * 格式化数据返回 小数点后两位表示
     */
    public static String toTwoDianStringSeparator(BigDecimal doubleValue) {
        if (doubleValue == null) {
            return "--";        }
        DecimalFormat format = new DecimalFormat("#,##0.00");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(doubleValue);
    }

    /**
     * 格式化数据返回,整数
     */
    public static String toIntegerSeparator(BigDecimal doubleValue) {
        if (doubleValue == null) {
            return "--";        }
        DecimalFormat format = new DecimalFormat("#,##0");
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format.format(doubleValue);
    }

}
