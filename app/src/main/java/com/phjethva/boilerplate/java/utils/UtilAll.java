package com.phjethva.boilerplate.java.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;

public class UtilAll {

    public static boolean isAppInstalled(Context ctx, String strPkgName) {
        PackageManager pm = ctx.getPackageManager();
        try {
            pm.getPackageInfo(strPkgName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }

}
