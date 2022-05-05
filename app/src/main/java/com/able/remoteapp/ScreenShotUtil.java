package com.able.remoteapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.DisplayMetrics;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Create by 赵思琦 on 2022/5/5
 * email zsqandzyr@gmail.com
 */
public class ScreenShotUtil {

    public  static Bitmap screenshot(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dims = resources.getDisplayMetrics();

        String surfaceClassName = "";
        if (Build.VERSION.SDK_INT <= 17) {
            surfaceClassName = "android.view.Surface";
        } else {
            surfaceClassName = "android.view.SurfaceControl";
        }

        try {
            Class<?> c = Class.forName(surfaceClassName);
            Method method = c.getMethod("screenshot", new Class[]{int.class, int.class});
            method.setAccessible(true);
           Bitmap bmp = (Bitmap) method.invoke(c, dims.widthPixels, dims.heightPixels);
        /*    bmp.setHasAlpha(false);
            bmp.prepareToDraw();*/
            return bmp;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
