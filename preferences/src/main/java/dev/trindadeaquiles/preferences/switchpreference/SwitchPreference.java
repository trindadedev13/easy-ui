package dev.trindadeaquiles.preferences.switchpreference;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import dev.trindadeaquiles.view.TrindadeSwitch;
import dev.trindadeaquiles.preferences.PreferencesUtil;

public class SwitchPreference {
    
    public LinearLayout root, preferenceRoot, textContainer, switchContainer;
    public TextView titleView, subtitleView;
    public TrindadeSwitch switchView;
    
    public LinearLayout.LayoutParams preferenceRootParams;
    
    public Context mContext;
    
    public int colorBackground = Color.parseColor("#FFFFFF");
    
    public SwitchPreference (Context ctx, LinearLayout root) {
        mContext = ctx;
        this.root = root;
    } 
    
    public void addSwitchPreference(String title, String subtitle, boolean defaultValue, CompoundButton.OnCheckedChangeListener onCheckedChangeListener, View.OnClickListener onClickListener) {      
        createPreferenceContainer();
        createTextContainer();
        createTitle(title);
        createSubtitle(subtitle);
        createSwitchContainer();
        createSwitch(onCheckedChangeListener, onClickListener);
        switchView.setChecked(defaultValue);
    }
    
    private void createPreferenceContainer(){
        preferenceRoot = new LinearLayout(mContext);
        preferenceRootParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.0f);
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
        titleView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        titleView.setText(text);
        titleView.setTextSize(16);
        textContainer.addView(titleView);
    }
    
    private void createSubtitle(String text){
        subtitleView = new TextView(mContext);
        subtitleView.setLayoutParams(new ViewGroup.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        subtitleView.setText(text);
        subtitleView.setTextSize(12);
        textContainer.addView(subtitleView);
    }
    
    private void createSwitchContainer(){
        switchContainer = new LinearLayout(mContext);
        switchContainer.setLayoutParams(new LinearLayout.LayoutParams(  LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT, 0.0f ));
        switchContainer.setGravity(Gravity.CENTER);
        switchContainer.setOrientation(LinearLayout.VERTICAL);
        switchContainer.setPadding(  8, 8,  8,  8);
        preferenceRoot.addView(switchContainer);
    }
    
    private void createSwitch(CompoundButton.OnCheckedChangeListener listener, View.OnClickListener listener_2){
        switchView = new TrindadeSwitch(mContext);
        switchView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        switchView.setPadding(8, 8, 8, 8);
        switchView.setTextSize(12);
        switchContainer.addView(switchView);
        switchView.setOnCheckedChangeListener(listener);
        preferenceRoot.setOnClickListener(listener_2);
    }
}
