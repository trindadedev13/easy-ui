package com.trindade.catalog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Toast;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import com.trindade.R;
import com.trindade.databinding.ActivityPrefsBinding;

import com.trindade.preferences.preference.Preference;
import com.trindade.preferences.inputpreference.InputPreference;
import com.trindade.preferences.switchpreference.SwitchPreference;

public class PreferencesActivity extends AppCompatActivity {

    private ActivityPreferencesBinding binding;
    public static int DEFAULT_BACKGROUND_COLOR = Color.parseColor("#fafafa");
    
    private Preference normalPreference;
    private InputPreference inputPreference;
    private SwitchPreference switchPreference;
            
    private View.OnClickListener positiveButtonListener = v -> {
        showToast(inputPreference.getDataTyped());
        inputPreference.closeInputDialog();
    };
        
    private View.OnClickListener negativeButtonListener = v -> {
        showToast("Canceled by user");
        inputPreference.closeInputDialog();
    };  
        
    
    private View.OnClickListener preferenceClickListener = v -> {
        showToast("Preference clicked");
    };
    
    private View.OnClickListener inputClickListener = v -> {
        inputPreference.inputDialog("Your name", "Type your name to continue", "name", "", positiveButtonListener, negativeButtonListener);
    };
    
    private View.OnClickListener switchClickListener = v -> {
        switchPreference.switchView.setChecked(!switchPreference.switchView.isChecked());
        showToast("Switch Preference Clicked" + switchPreference.switchView.isChecked());
    };

    private CompoundButton.OnCheckedChangeListener checkedListener = (buttonView, isChecked) -> {
       showToast("Switch Clicked: " + isChecked);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPreferencesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }
    
    private void init() {
        setDefaultBackgroundColor(this);
        normalPreference = new Preference(this, binding.rootV);
        inputPreference = new InputPreference(this, binding.rootV);
        switchPreference = new SwitchPreference(this, binding.rootV);
        preferences(normalPreference, inputPreference, switchPreference);
    }
    
    private void preferences(Preference preference, InputPreference inputPreference, SwitchPreference switchPreference) {
        preference.colorBackground = DEFAULT_BACKGROUND_COLOR;
        inputPreference.colorBackground = DEFAULT_BACKGROUND_COLOR;
        switchPreference.colorBackground = DEFAULT_BACKGROUND_COLOR;
        
        preference.addPreference("Preference title", "Preference subtitle", preferenceClickListener);
        inputPreference.addDialogInputPreference("DialogInputPreference title", "DialogInputPreference subtitle", inputClickListener);
        switchPreference.addSwitchPreference("SwitchPreference title", "SwitchPreference subtitle", false, checkedListener, switchClickListener);
    }
    
    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
    
    public int getColorFromStyle(Context context, int styleID, int attributeID) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attributeID, typedValue, true);
        return typedValue.data;
    }

    public void setDefaultBackgroundColor(Context context) {
        DEFAULT_BACKGROUND_COLOR = Color.parseColor("#FFFFFF");
    }
    
}