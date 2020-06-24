package com.phjethva.boilerplate.java.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UtilSharedPref {

    public static void putSPInt(Context ctx, String key, int i) {
        SharedPreferences sp = ctx.getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, i);
        editor.commit();
    }

    public static Integer getSPInt(Context ctx, String key) {
        SharedPreferences sp = ctx.getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
        int i = sp.getInt(key, 1);
        return i;
    }

    public static void putSPBol(Context ctx, String key, boolean bol) {
        SharedPreferences sp = ctx.getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, bol);
        editor.commit();
    }

    public static boolean getSPBol(Context ctx, String key) {
        SharedPreferences sp = ctx.getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
        boolean bol = sp.getBoolean(key, false);
        return bol;
    }

    public static void putSPStr(Context ctx, String key, String i) {
        SharedPreferences sp = ctx.getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, i);
        editor.commit();
    }

    public static String getSPStr(Context ctx, String key) {
        SharedPreferences sp = ctx.getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE);
        String i = sp.getString(key, "");
        return i;
    }

}
