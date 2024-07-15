package dev.trindadeaquiles.lib.preferences.preference;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import dev.trindadeaquiles.lib.preferences.PreferencesUtil;

public class Preference {

    public LinearLayout root, preferenceRoot, textContainer;
    public TextView titleView, subtitleView;
    public Context mContext;
    
    public int colorBackground = Color.parseColor("#FFFFFF");
    
    public Preference (Context ctx, LinearLayout root) {
        mContext = ctx;
        this.root = root;
    } 
    
    public void addPreference(String title, String subtitle, View.OnClickListener listener) {
        createPreferenceContainer();
        createTextContainer();
        createTitle(title);
        createSubtitle(subtitle);
        preferenceRoot.setOnClickListener(listener);
    }
    
    private void createPreferenceContainer(){
        preferenceRoot = new LinearLayout(mContext);
        LinearLayout.LayoutParams preferenceRootParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.0f);
        preferenceRootParams.bottomMargin = 4;
        preferenceRoot.setLayoutParams(preferenceRootParams);
        preferenceRoot.setBackgroundColor(colorBackground);
        preferenceRoot.setOrientation(LinearLayout.HORIZONTAL);
        preferenceRoot.setPadding(10, 10, 10, 10);
        preferenceRoot.setBaselineAligned(false);
        PreferencesUtil.cardStyle(preferenceRoot, mContext, colorBackground);
        root.addView(preferenceRoot);
    }
    
    private void createTextContainer(){
        textContainer = new LinearLayout(mContext);
        textContainer.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f));
        textContainer.setOrientation(LinearLayout.VERTICAL);
        textContainer.setPadding(8, 8, 8, 8);
        preferenceRoot.addView(textContainer);
    }
    
    private void createTitle(String text){
        titleView = new TextView(mContext);
        titleView.setLayoutParams (new ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        titleView.setText(text);
        titleView.setTextSize(16);
        textContainer.addView(titleView);
    }
    
    private void createSubtitle(String text){
        subtitleView = new TextView(mContext);
        subtitleView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        subtitleView.setText(text);
        subtitleView.setTextSize(12);
        textContainer.addView(subtitleView);
    }
}
