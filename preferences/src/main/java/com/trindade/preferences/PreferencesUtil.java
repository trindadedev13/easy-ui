package com.trindade.preferences;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;

public class PreferencesUtil {

    public static void cardStyle(View view, Context context, int DEFAULT_BACKGROUND_COLOR) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        int leftMargin = 10;
        int topMargin = 4;
        int rightMargin = 10;
        int bottomMargin = 4;
        layoutParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        view.setLayoutParams(layoutParams);
        GradientDrawable trindadeView = new GradientDrawable();
        trindadeView.setColor(DEFAULT_BACKGROUND_COLOR);
        trindadeView.setCornerRadii(new float[]{26f, 26f, 26f, 26f, 26f, 26f, 26f, 26f});
        android.content.res.ColorStateList colorStateList = android.content.res.ColorStateList.valueOf(Color.parseColor("#616161"));
        RippleDrawable rippleDrawable = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            rippleDrawable = new RippleDrawable(colorStateList, trindadeView, null);
        } else {
            view.setBackground(trindadeView);
        }
        view.setBackground(rippleDrawable);
    }
}