package com.orbyun.utils.helper;

import android.text.TextUtils;

import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author youyang
 */
public class StringHelper {
    private static StringHelper instance = null;

    private StringHelper() {
    }

    public static StringHelper getInstance() {
        if (null == instance) {
            instance = new StringHelper();
        }
        return instance;
    }


    /**
     * 判断是否为空，空返回true
     */
    public boolean isEmpty(String str) {
        if (null == str)
            str = "";
        return "".equals(str.trim());
    }

    public static String empty(String str) {
        if (TextUtils.isEmpty(str)) {
            return "--";
        } else {
            return str;
        }
    }

    /**
     * ss判断是否为空，空返回true
     */
    public boolean isRealEmpty(String str) {
        if (isEmpty(str) || "null".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否不为空，不空返回true
     */
    public boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断两个字符串是否相等
     *
     * @param str1
     * @param str2
     * @return 相等返回true
     */
    public boolean isEqual(String str1, String str2) {
        if (isEmpty(str1) && isEmpty(str2))
            return true;
        else if (isEmpty(str1) || isEmpty(str2))
            return false;
        else
            return str1.trim().equals(str2.trim());
    }

    /**
     * 去除所有空格,包括中间的
     */
    public String removeAllSpace(String str) {
        if (isEmpty(str))
            return "";

        return str.replaceAll("\\s+", "");
    }

    /**
     * String.trim
     */
    public String trim(String str) {
        if (isEmpty(str))
            return "";

        return str.trim();
    }

    /**
     * 判断字符串数组是否为空
     *
     * @param strs
     * @return 不空返回true
     */
    public boolean isNotEmpty(String[] strs) {
        return strs != null && strs.length != 0;
    }

    /**
     * 将字符串转换为数字
     */
    public int convertToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return Integer.MAX_VALUE;
        }
    }

    /**
     * 将字符串转换为Integer
     */
    public Integer convertToInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 去掉 resource中的空元素
     *
     * @param resource
     * @return
     */
    public String[] removeNull(String[] resource) {
        List<String> target = new LinkedList<String>();

        if (!isNotEmpty(resource))
            return null;

        for (String s : resource) {
            if (isNotEmpty(s))
                target.add(s);
        }

        return target.toArray(new String[target.size()]);
    }

    /**
     * 封装String类的split
     *
     * @param str
     * @param symbol
     * @return
     */
    public String[] split(String str, String symbol) {
        if (isEmpty(str))
            return null;

        return str.split(trim(symbol));
    }

    /**
     * 将首字母变为大写
     *
     * @param str
     * @return
     */
    public String toUperFirstChar(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public String convertString(String str) {
        return str.replace('.', '/');
    }

    /**
     * 编码
     *
     * @param str
     * @return
     */
    public String encode(String str) {
        try {
            return isNotEmpty(str) ? URLEncoder.encode(str, "utf-8")
                    : "";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 解码
     *
     * @param str
     * @return
     */
    public String decode2(String str) {
        try {
            return isNotEmpty(str) ? URLDecoder.decode(str, "utf-8") : "";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 将对象转至String类型
     *
     * @param obj
     * @return
     */
    public String convertToString(Object obj) {
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }

    /**
     * 将filepath转至url
     *
     * @param file
     * @return
     */
    public String filePathToURL(File file) {
        return file == null ? "" : filePathToURL(file.getPath());
    }

    /**
     * 将filepath转至url
     *
     * @param filePath
     * @return
     */
    public String filePathToURL(String filePath) {
        if (isNotEmpty(filePath))
            return filePath.replace("\\", "//");
        return null;
    }
    // 获取一定为数的字符串 s代表填充的字符

    public String getNumberStrWithZero(String number, int length,
                                       String s) {

        String dd = "";
        for (int i = 0; i < length - number.length(); i++)
            dd = dd + s;

        return dd + number;
    }

    /**
     * 判断字符串中是否包含汉字
     *
     * @param str
     * @return boolean
     */
    public boolean isIncludeChinese(String str) {
        boolean isExists = false;
        int flag = 0;
        for (int index = 0; index < str.length(); index++) {
            flag = getCharType(str.charAt(index));
            if (flag == 2) {
                isExists = true;
                break;
            }
        }
        return isExists;
    }

    /**
     * 判断输入字符是字母或数字、中文字符、还是其它 返回值(int)： 0： 数字字符 1： 英文字符 2： 中文字符 -1：其它字符
     */
    public int getCharType(char ch) {

        switch (Character.getType(ch)) {
            case Character.DECIMAL_DIGIT_NUMBER:
                return 0;
            case Character.LOWERCASE_LETTER:
            case Character.UPPERCASE_LETTER:
                return 1;
            case Character.OTHER_LETTER:
                return 2;
            default:
                return -1;
        }
    }

    public String convertNullString(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        return str.trim();
    }

    public String convertNullObject(Object str) {
        if (str == null || str.equals("")) {
            return "";
        }
        return str.toString();
    }

    /**
     * Check that the given CharSequence is neither <code>null</code> nor of
     * length 0. Note: Will return <code>true</code> for a CharSequence that
     * purely consists of whitespace.
     * <p>
     *
     * <pre>
     * StringUtils.hasLength(null) = false
     * StringUtils.hasLength("") = false
     * StringUtils.hasLength(" ") = true
     * StringUtils.hasLength("Hello") = true
     * </pre>
     *
     * @param str the CharSequence to check (may be <code>null</code>)
     * @return <code>true</code> if the CharSequence is not null and has length
     */
    public boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    /**
     * Check that the given String is neither <code>null</code> nor of length 0.
     * Note: Will return <code>true</code> for a String that purely consists of
     * whitespace.
     *
     * @param str the String to check (may be <code>null</code>)
     * @return <code>true</code> if the String is not null and has length
     * @see #hasLength(CharSequence)
     */
    public boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    /**
     * Replace all occurences of a substring within a string with another
     * string.
     *
     * @param inString   String to examine
     * @param oldPattern String to replace
     * @param newPattern String to insert
     * @return a String with the replacements
     */
    public String replace(String inString, String oldPattern,
                          String newPattern) {
        if (!hasLength(inString) || !hasLength(oldPattern)
                || newPattern == null) {
            return inString;
        }
        StringBuilder sb = new StringBuilder();
        int pos = 0; // our position in the old string
        int index = inString.indexOf(oldPattern);
        // the index of an occurrence we've found, or -1
        int patLen = oldPattern.length();
        while (index >= 0) {
            sb.append(inString.substring(pos, index));
            sb.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sb.append(inString.substring(pos));
        // remember to append any characters to the right of a match
        return sb.toString();
    }

    /**
     * Replace all occurences of a substring within a string with another
     * string.
     *
     * @param inString   String to examine
     * @param oldPattern String to replace
     * @param newPattern String to insert
     * @return a String with the replacements
     */
    public String replaceLast(String inString, String oldPattern, String newPattern) {
        if (!hasLength(inString) || !hasLength(oldPattern)
                || newPattern == null) {
            return inString;
        }
        StringBuilder sb = new StringBuilder();
        int pos = 0; // our position in the old string
        int index = inString.lastIndexOf(oldPattern);
        // the index of an occurrence we've found, or -1
        int patLen = oldPattern.length();
        if (index >= 0) {
            sb.append(inString.substring(pos, index));
            sb.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sb.append(inString.substring(pos));
        // remember to append any characters to the right of a match
        return sb.toString();
    }

    /**
     * 对中文字符encodeURIComponent编码的解码。
     * 注意由于url可能包含“％”，所有才做这个操作，而且，url同时进行了encodeURI编码
     */
    public String decode(String str) {
        try {
            while (str.indexOf("{") != -1 && str.indexOf("}") != -1) {
                String ss = str.substring(str.indexOf("{") + 1,
                        str.indexOf("}"));
                String ass = URLDecoder.decode(
                        URLDecoder.decode(ss, "UTF-8"), "UTF-8");
                str = str.replaceAll("\\{" + ss + "\\}", ass);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * get length of Chinese characters a Chinese characters as two characters
     */
    public int getCwordLength(String para) {
        if (para == null)
            throw new IllegalArgumentException("Strings must not be null");
        int length = 0;
        for (int i = 0; i < para.length(); i++) {
            if (Character.getType(para.charAt(i)) == 5) {
                length += 2;
            } else
                length++;
        }
        return length;
    }

    public int getdatabaseLength(String para) {
        int length = 0;

        for (int i = 0; i < para.length(); i++) {
            if (Character.getType(para.charAt(i)) == 5) {
                length += 2;
            } else
                length++;
        }
        return length;
    }

    public void main(String[] args) {

    }


    /**
     * 按字符截取带汉字的字符串
     *
     * @return
     */
//		public String subString(String str, int num) {
//		    char[] charArray = str.toCharArray();
//		    String subStr = "";
//		    int index = 0;
//		    if(charArray.length<=num){
//		    	return str;
//		    }else{
//		    	if(Character.getType(charArray[num-1]) == Character.OTHER_LETTER){
//		    		return str.substring(0, num-2);
//		    	}else{
//		    		return str.substring(0, num-1);
//		    	}
//		    	
//		    	
//		    	/*//i是字符数，j字符串中元素的个数
//		    	for (int d = 0, j = 0; d < num;) {
//				      char tempchar = charArray[j];
//				      if (Character.getType(tempchar) == Character.OTHER_LETTER) {
//				        // 如果这个字符是一个汉字，加两个字符
//				        d = d + 2;
//				        if (d > num) {
//				          // 如果num正好截取到半个汉字的时候，跳过此次for循环。
//				          // 如果num正好截取到一个完整的汉字的时候，继续执行下面的index++等语句。
//				          if (d == (num+ 1)&&charArray.length>=num+1) {
//				            continue;
//				          }
//				        }
//				      }else {
//				        d++;
//				      }
//				      index++;
//				      j++;
//				    }
//				    subStr = str.substring(0, index);
//				    return subStr;
//				  
//		    	
//		    
//		    */
//		    }
//		   }
//		  
    public String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public String splitFileNameFromPath(String path) {
        if (null == path) {
            return null;
        }
        String name = path.substring(path.lastIndexOf("/") + 1, path.length());
        return name;
    }

    /**
     * 使用正则表达式,去除指定字符串中的空格、回车、换行符、制表符
     */
    public String replaceChars(String strContent) {
        String newString = "";
        Pattern pattern = null;
        Matcher matcher = null;
        try {
            if (null == strContent || strContent.equalsIgnoreCase("")) {
                return newString;
            }
            pattern = Pattern.compile("//s*|/t|/r|/n");
            matcher = pattern.matcher(strContent);
            newString = matcher.replaceAll("");
            newString = newString.trim();
            newString = newString.replace("/n", "");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return newString;
        }
    }

    /**
     * 将json格式的字符串解析成Map对象 <li>
     * json格式：
     * {
     * "sex": "0",
     * "extType": "newsReply",
     * "isHeadline": "0",
     * "messageContent": “今天下午离开",
     * "beginTime": "2015-08-20 15:08:33",
     * "endTime": "2015-09-19 15:08:33"
     * }
     */
    public HashMap<String, String> toHashMap(Object object) {
        JSONObject jsonObject = null;
        String key = "";
        String value = "";
        HashMap<String, String> data = null;
        try {
            // 将json字符串转换成jsonObject
            jsonObject = new JSONObject((String) object);
            Iterator it = jsonObject.keys();
            if (null != it) {
                data = new HashMap<String, String>();
            }
            // 遍历jsonObject数据，添加到Map对象
            while (it.hasNext()) {
                key = String.valueOf(it.next());
                value = (String) jsonObject.get(key);
                data.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return data;
        }
    }

}
