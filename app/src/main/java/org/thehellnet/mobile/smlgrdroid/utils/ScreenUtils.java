package org.thehellnet.mobile.smlgrdroid.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by sardylan on 16/06/15.
 */
public final class ScreenUtils {
    private static DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();

    public static int dpToPx(int dp) {
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    public static int pxToDp(int px) {
        return (int) ((px / displayMetrics.density) + 0.5);
    }
}
