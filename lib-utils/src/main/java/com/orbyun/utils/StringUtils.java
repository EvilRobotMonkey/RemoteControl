package com.orbyun.utils;

import android.text.TextUtils;
import android.view.TextureView;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @package com.boci.smart.utils
 * @file StringUtils
 * @date 2018/9/3  下午4:48
 * @autor wangxiongfeng
 */
public class StringUtils {
    public static int chineseNumber2Int(String chineseNumber) {
        int result = 0;
        int temp = 1;//存放一个单位的数字如：十万
        int count = 0;//判断是否有chArr
        char[] cnArr = new char[]{'一', '二', '三', '四', '五', '六', '七', '八', '九'};
        char[] chArr = new char[]{'十', '百', '千', '万', '亿'};
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean b = true;//判断是否是chArr
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
                if (c == cnArr[j]) {
                    if (0 != count) {//添加下一个单位之前，先把上一个单位值添加到结果中
                        result += temp;
                        temp = 1;
                        count = 0;
                    }
                    // 下标+1，就是对应的值
                    temp = j + 1;
                    b = false;
                    break;
                }
            }
            if (b) {//单位{'十','百','千','万','亿'}
                for (int j = 0; j < chArr.length; j++) {
                    if (c == chArr[j]) {
                        switch (j) {
                            case 0:
                                temp *= 10;
                                break;
                            case 1:
                                temp *= 100;
                                break;
                            case 2:
                                temp *= 1000;
                                break;
                            case 3:
                                temp *= 10000;
                                break;
                            case 4:
                                temp *= 100000000;
                                break;
                            default:
                                break;
                        }
                        count++;
                    }
                }
            }
            if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
                result += temp;
            }
        }
        return result;
    }

    public static String isEmpty(String str) {

        if (TextUtils.isEmpty(str)) {
            return "--";
        }
        if (TextUtils.isEmpty(str.trim())) {
            return "--";
        }

        return str;
    }

    //    private static String idNumRegular = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";
    private static String idNumRegular = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

    public static boolean isCardID(String idNum) {
        // 定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）
        Pattern idNumPattern = Pattern.compile(idNumRegular);
        // 通过Pattern获得Matcher
        Matcher idNumMatcher = idNumPattern.matcher(idNum);
        // 判断用户输入是否为身份证号
        if (idNumMatcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean regexStr(String content, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static String addStrMask(String str, int start, int end) {
        try {
            if (TextUtils.isEmpty(str))
                return str;
            StringBuffer sb = new StringBuffer();
            sb.append(str.substring(0, start));
            for (int i = 0; i < end; i++) {
                sb.append("*");
            }
            sb.append(str.substring(end - 1, str.length() - 1));
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static boolean isEmptyArr(String[] str) {
        int count = 0;
        if (str == null)
            return true;
        if (str != null && str.length == 0) {
            return true;
        }
        for (int i = 0; i < str.length; i++) {
            if (str[i] == null) {
                count++;
            }
        }
        if (count == str.length) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据身份证的号码算出当前身份证持有者的性别和年龄 18位身份证
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> getCarInfo(String CardCode) throws Exception {

        if (TextUtils.isEmpty(CardCode))
            return null;
        Map<String, Object> map = new HashMap<String, Object>();
        String year = CardCode.substring(6).substring(0, 4);// 得到年份
        String yue = CardCode.substring(10).substring(0, 2);// 得到月份
        // String day=CardCode.substring(12).substring(0,2);//得到日
        String sex;
        if (Integer.parseInt(CardCode.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别
            sex = "女士";
        } else {
            sex = "先生";
        }
        Date date = new Date();// 得到当前的系统时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fyear = format.format(date).substring(0, 4);// 当前年份
        String fyue = format.format(date).substring(5, 7);// 月份
        // String fday=format.format(date).substring(8,10);
        int age = 0;
        if (Integer.parseInt(yue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
            age = Integer.parseInt(fyear) - Integer.parseInt(year) + 1;
        } else {// 当前用户还没过生
            age = Integer.parseInt(fyear) - Integer.parseInt(year);
        }
        map.put("sex", sex);
        map.put("age", age);
        return map;
    }


    private static String SPACE = "   ";

    /**
     * 返回格式化JSON字符串。
     *
     * @param json 未格式化的JSON字符串。
     * @return 格式化的JSON字符串。
     */
    public static String formatJson(String json) {
        StringBuffer result = new StringBuffer();

        int length = json.length();
        int number = 0;
        char key = 0;
        //遍历输入字符串。
        for (int i = 0; i < length; i++) {
            //1、获取当前字符。
            key = json.charAt(i);

            //2、如果当前字符是前方括号、前花括号做如下处理：
            if ((key == '[') || (key == '{')) {
                //（1）如果前面还有字符，并且字符为“：”，打印：换行和缩进字符字符串。
                if ((i - 1 > 0) && (json.charAt(i - 1) == ':')) {
                    result.append('\n');
                    result.append(indent(number));
                }

                //（2）打印：当前字符。
                result.append(key);

                //（3）前方括号、前花括号，的后面必须换行。打印：换行。
                result.append('\n');

                //（4）每出现一次前方括号、前花括号；缩进次数增加一次。打印：新行缩进。
                number++;
                result.append(indent(number));

                //（5）进行下一次循环。
                continue;
            }

            //3、如果当前字符是后方括号、后花括号做如下处理：
            if ((key == ']') || (key == '}')) {
                //（1）后方括号、后花括号，的前面必须换行。打印：换行。
                result.append('\n');

                //（2）每出现一次后方括号、后花括号；缩进次数减少一次。打印：缩进。
                number--;
                result.append(indent(number));

                //（3）打印：当前字符。
                result.append(key);

                //（4）如果当前字符后面还有字符，并且字符不为“，”，打印：换行。
                if (((i + 1) < length) && (json.charAt(i + 1) != ',')) {
                    result.append('\n');
                }

                //（5）继续下一次循环。
                continue;
            }

            //4、如果当前字符是逗号。逗号后面换行，并缩进，不改变缩进次数。
            if ((key == ',')) {
                result.append(key);
                result.append('\n');
                result.append(indent(number));
                continue;
            }

            //5、打印：当前字符。
            result.append(key);
        }

        return result.toString();
    }

    /**
     * 返回指定次数的缩进字符串。每一次缩进三个空格，即SPACE。
     *
     * @param number 缩进次数。
     * @return 指定缩进次数的字符串。
     */
    private static String indent(int number) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < number; i++) {
            result.append("   ");
        }
        return result.toString();
    }

    /**
     * 将汉字转换为全拼
     *
     * @param src
     * @return
     */
    public static String getPinYin(String src) {
        char[] hz = null;
        hz = src.toCharArray();//该方法的作用是返回一个字符数组，该字符数组中存放了当前字符串中的所有字符
        String[] py = new String[hz.length];//该数组用来存储
        //设置汉子拼音输出的格式
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        StringBuffer sb = new StringBuffer(); //存放拼音字符串
        int len = hz.length;

        try {
            for (int i = 0; i < len; i++) {
                //先判断是否为汉字字符
                if (Character.toString(hz[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    //将汉字的几种全拼都存到py数组中
                    py = PinyinHelper.toHanyuPinyinStringArray(hz[i], format);
                    //取出改汉字全拼的第一种读音，并存放到字符串pys后

                    sb.append(py[0]);
                    sb.append(",");
                } else {
                    //如果不是汉字字符，间接取出字符并连接到 pys 后
                    sb.append(hz[i]);
                    sb.append(",");
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }

        if (sb.length() < 1) {
            return "";

        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    /**
     * 提取每个汉字的首字母
     *
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int i = 0; i < str.length(); i++) {
            char word = str.charAt(i);
            //提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert.toUpperCase();
    }

    /**
     * 将字符串转换成ASCII码
     */
    public static String getCnASCII(String str) {
        StringBuffer buf = new StringBuffer();
        //将字符串转换成字节序列
        byte[] bGBK = str.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            //将每个字符转换成ASCII码
            buf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return buf.toString();
    }

    public static int getNumleng(String string) {
        if (TextUtils.isEmpty(string))
            return 0;

        int count = 0;
        String regex = "[0-9*a-z]+";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                String group = matcher.group(i);
                int length = group.length();
                count += length;
            }
        }
        return count;

    }


}
