package com.orbyun.utils.helper;

import android.content.Context;


public class DisplayHelper {
    private static DisplayHelper instance = new DisplayHelper();

    private DisplayHelper(){};

    public static DisplayHelper getInstance() {
        return instance;
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * @param pxValue
     * @param scale
     *            （DisplayMetrics类中属性density）
     * @return
     */
    public int px2dip(float pxValue, float scale) {
        return (int) (pxValue / scale + 0.5f);
    }
    public int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * @param dipValue
     * @param scale
     *            （DisplayMetrics类中属性density）
     * @return
     */
    public int dip2px(float dipValue, float scale) {
        return (int) (dipValue * scale + 0.5f);
    }
    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * @param dipValue
     *            （DisplayMetrics类中属性density）
     * @return
     */
    public int dip2px(Context context,float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * @param pxValue
     * @param fontScale
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public int px2sp(float pxValue, float fontScale) {
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param spValue
     * @param fontScale
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public int sp2px(float spValue, float fontScale) {
        return (int) (spValue * fontScale + 0.5f);
    }
    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param context
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param context
     * @param pxValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}