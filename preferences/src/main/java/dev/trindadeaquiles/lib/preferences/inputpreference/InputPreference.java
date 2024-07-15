package dev.trindadeaquiles.lib.preferences.inputpreference;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.LayoutInflater; 

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import dev.trindadeaquiles.lib.R;
import dev.trindadeaquiles.lib.preferences.PreferencesUtil; 

public class InputPreference {
    
    public LinearLayout root, preferenceRoot, textContainer;
    public TextView titleView, subtitleView;
    public Context mContext;
    public String POSITIVE_BUTTON_TEXT, NEGATIVE_BUTTON_TEXT;
    public String typedVal;
    
    public EditText inputText;
    public AlertDialog dialog;
    public Button positiveButton , negativeButton;
    
    public int colorBackground = Color.parseColor("#FFFFFF");
    
    public InputPreference (Context ctx, LinearLayout root) {
        mContext = ctx;
        this.root = root;
        POSITIVE_BUTTON_TEXT = mContext.getString(R.string.common_ok);
        NEGATIVE_BUTTON_TEXT = mContext.getString(R.string.common_cancel);
    } 

    public void addDialogInputPreference(String title, String subtitle, View.OnClickListener listener) {
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
    
    public void inputDialog(String title, String message, String hint, String defaultVal, View.OnClickListener listenerButtonOK, View.OnClickListener listenerButtonCancel){
       LayoutInflater inflater = LayoutInflater.from(mContext);
       View dialogView = inflater.inflate(R.layout.dialog_input_layout, null);
       EditText inputText = dialogView.findViewById(R.id.inputText);
       inputText.setHint(hint);
       inputText.setText(defaultVal);

      dialog = new MaterialAlertDialogBuilder(mContext)
          .setView(dialogView)
          .setTitle(title)
          .setMessage(message)
          .setNegativeButton(NEGATIVE_BUTTON_TEXT, null)
          .setPositiveButton(POSITIVE_BUTTON_TEXT, null)
          .create();

      dialog.setOnShowListener(dialogInterface -> {
          Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
          Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);

          positiveButton.setOnClickListener(listenerButtonOK);
          negativeButton.setOnClickListener(listenerButtonCancel);

          dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
          inputText.requestFocus();
      });

      dialog.show();
    }
    
    public String getDataTyped(){
        return inputText.getText().toString();
    }
    
    public void closeInputDialog(){
        dialog.dismiss();
    }
    
}
