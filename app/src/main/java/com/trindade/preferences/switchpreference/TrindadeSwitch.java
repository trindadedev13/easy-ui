package com.trindade.preferences.switchpreference;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.SwitchCompat;

import com.trindade.R;

public class TrindadeSwitch extends SwitchCompat {

    public TrindadeSwitch(Context context) {
        super(context);
        init(context, null);
    }

    public TrindadeSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TrindadeSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setPadding(8, 8, 8, 8);
        setText("Switch");
        setTextSize(16);
        setTextColor(context.getResources().getColor(R.color.colorOnSurfaceVariant));
        setThumbDrawable(context.getResources().getDrawable(R.drawable.ui_m3_switch_thumb));
        setTrackDrawable(context.getResources().getDrawable(R.drawable.ui_m3_switch_track));
        setTrackTintList(context.getResources().getColorStateList(R.color.sel_m3_switch_track));
        setThumbTintList(context.getResources().getColorStateList(R.color.sel_m3_switch_thumb));
    }
}