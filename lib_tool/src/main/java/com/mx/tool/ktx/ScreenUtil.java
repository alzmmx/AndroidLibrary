package com.mx.tool.ktx;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;


public class ScreenUtil {

    private static int screenW = -1;
    private static int screenH = -1;
    private static float screenDensity = -1;
    private static float screenScaledDensity = -1;

    /**
     * 在activity或Application中进行初始化
     */
    public static void initScreen(Context context) {
        checkContext(context);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenW = displayMetrics.widthPixels;
        screenH = displayMetrics.heightPixels;
        screenDensity = displayMetrics.density;
        screenScaledDensity = displayMetrics.scaledDensity;
    }

    /**
     * @return ：获取屏幕的宽
     */
    public static int getWidth() {
        return screenW;
    }

    /**
     * @return ：获取屏幕的高
     */
    public static int getHeight() {
        return screenH;
    }

    /**
     * dp转换为px
     */
    public static int dp2px(float dpValue) {
        return (int) (dpValue * screenDensity + 0.5);
    }

    /**
     * px转换为dp
     */
    public static float px2dp(int pxValue) {
        return pxValue * 1.0f / screenDensity;
    }

    /**
     * sp转换为px
     */
    public static int sp2px(float spValue) {
        return (int) (spValue * screenScaledDensity + 0.5);
    }

    /**
     * px转换为sp
     */
    public static float px2sp(int pxValue) {
        return pxValue * 1.0f / screenScaledDensity;
    }

    /**
     * 对初始化时的context参数进行校验
     */
    private static void checkContext(Context context) {
        if (!(context instanceof Activity || context instanceof Application)) {
            throw new IllegalArgumentException("传入的context类型不对");
        }
    }

}
