package com.phjethva.boilerplate.toast.java;

import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.phjethva.boilerplate.toast.R;

public class UtilToast {

    public static void pjToastSquare(Context ctx, String str) {
        Toast toast = Toast.makeText(ctx, str, Toast.LENGTH_SHORT);
        View vwToast = toast.getView();
        vwToast.setBackground(ContextCompat.getDrawable(ctx, R.drawable.bg_toast));
        vwToast.setPadding(50, 20, 50, 20);
        TextView tvToast = (TextView) vwToast.findViewById(android.R.id.message);
        tvToast.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        if (Build.VERSION.SDK_INT < 23) {
            tvToast.setTextColor(ctx.getResources().getColor(R.color.colorPrimary));
        } else {
            tvToast.setTextColor(ContextCompat.getColor(ctx, R.color.colorPrimary));
        }
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 50);
        toast.show();
    }

}
