package com.orbyun.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * 获得屏幕相关的辅助类
 * 
 */
public class ScreenUtils
{
    private static final String TAG = "ScreenUtils";
	private ScreenUtils()
	{
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 获得屏幕宽度
	 *
	 */
	public static int getScreenWidth(Context context)
	{
        WindowManager wm = null;
        DisplayMetrics outMetrics = null;
        int widthPixels = 0;
        try {
            wm = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            outMetrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(outMetrics);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != outMetrics) {
                widthPixels =  outMetrics.widthPixels;
            }
            return widthPixels;
        }
	}

	/**
	 * 获得屏幕高度
	 */
	public static int getScreenHeight(Context context)
	{
        WindowManager wm = null;
        DisplayMetrics outMetrics = null;
        int heightPixels = 0;
        try {
            wm = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            outMetrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(outMetrics);
            if (null != outMetrics) {
                heightPixels =  outMetrics.heightPixels;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG,e.getMessage());
        } finally {
            return heightPixels;
        }
	}

	/**
	 * 获得状态栏的高度
	 */
	public static int getStatusHeight(Context context)
	{
		int statusHeight = -1;
		try
		{
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height")
					.get(object).toString());
			statusHeight = context.getResources().getDimensionPixelSize(height);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return statusHeight;
	}

	/**
	 * 获取当前屏幕截图，包含状态栏
	 * 
	 * @param activity
	 * @return
	 */
	public static Bitmap snapShotWithStatusBar(Activity activity)	{
        View view = null;
        Bitmap bp = null;
		try {
            view = activity.getWindow().getDecorView();
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            Bitmap bmp = view.getDrawingCache();
            int width = getScreenWidth(activity);
            int height = getScreenHeight(activity);
            bp = Bitmap.createBitmap(bmp, 0, 0, width, height);

        } catch (Exception e) {
            e.printStackTrace();
              Log.d(TAG,e.getMessage());
        } finally {
            view.setDrawingCacheEnabled(false);
            view.destroyDrawingCache();
            return bp;
        }
    }

	/**
	 * 获取当前屏幕截图，不包含状态栏
	 *
	 */
	public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = null;
        Bitmap bp = null;
        try {
            view = activity.getWindow().getDecorView();
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            Bitmap bmp = view.getDrawingCache();
            Rect frame = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            int statusBarHeight = frame.top;

            int width = getScreenWidth(activity);
            int height = getScreenHeight(activity);

            bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                    - statusBarHeight);

        } catch (Exception e) {
            e.printStackTrace();
              Log.d(TAG,e.getMessage());
        } finally {
            view.setDrawingCacheEnabled(false);
            view.destroyDrawingCache();
            return bp;
        }
	}

    /**
     * 自定义截取区域
     *
     */
    public static Bitmap snapShotCustom(View view) {
        Bitmap bp = null;
        try {
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            Bitmap bmp = view.getDrawingCache();
            int width = view.getWidth();
            int height = view.getHeight();

            bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        } catch (Exception e) {
            e.printStackTrace();
              Log.d(TAG,e.getMessage());
        } finally {
            view.setDrawingCacheEnabled(false);
            view.destroyDrawingCache();
            return bp;
        }
    }

    /**
     * 转换生成图片 create by lx 20150804
     * @param view
     * @return
     */
    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }
}
