package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.databinding.ActivityMainBinding;

import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        configurePreference(binding.pref);
        configurePreference(binding.prefWithIcon);
        configurePreferencePopup(binding.prefPopup);
        configurePreferencePopup(binding.prefWithIconPopup);
        configurePreferenceSwitch(binding.prefSwitch);
        configurePreferenceSwitch(binding.prefWithIconSwitch);
    }
    
    void configurePreferenceGroup(dev.trindadedev.lib.ui.components.preference.PreferenceGroup pref) {
        //pref.setTitle("Title");
    }
    
    void configurePreference(dev.trindadedev.lib.ui.components.preference.Preference pref) {
        // pref.setTitle("Title");
        // pref.setDescription("Description");
        // if use icon : pref.setIcon(R.drawable.your_image);
        pref.setOnClickListener(view -> {
            // your action
        });
    }
    
    void configurePreferencePopup(dev.trindadedev.lib.ui.components.preference.PreferencePopup pref) {
        // pref.setTitle("Title");
        // pref.setDescription("Description");
        // if use icon : pref.setIcon(R.drawable.your_image);
        pref.addPopupMenuItem("Item 1");
        pref.addPopupMenuItem("Item 2");
        pref.setMenuListener(item -> {
            // your action, toast example:
            Toast.makeText(this, item.getTitle().toString(), 4000).show();
            return true;
        });
    }
    
    void configurePreferenceSwitch(dev.trindadedev.lib.ui.components.preference.PreferenceSwitch pref) {
        // pref.setTitle("Title");
        // pref.setDescription("Description");
        // if use icon : pref.setIcon(R.drawable.your_image);
        pref.setSwitchChangedListener((buttonView, isChecked) -> { 
            // your action
        });
    }
    
    void configurePreference(dev.trindadedev.lib.ui.components.preference.withicon.Preference pref) {
        // pref.setTitle("Title");
        // pref.setDescription("Description");
        // if use icon : pref.setIcon(R.drawable.your_image);
        pref.setOnClickListener(view -> {
            // your action
        });
    }
    
    void configurePreferencePopup(dev.trindadedev.lib.ui.components.preference.withicon.PreferencePopup pref) {
        // pref.setTitle("Title");
        // pref.setDescription("Description");
        // if use icon : pref.setIcon(R.drawable.your_image);
        pref.addPopupMenuItem("Item 1");
        pref.addPopupMenuItem("Item 2");
        pref.setMenuListener(item -> {
            // your action, toast example:
            Toast.makeText(this, item.getTitle().toString(), 4000).show();
            return true;
        });
    }
    
    void configurePreferenceSwitch(dev.trindadedev.lib.ui.components.preference.withicon.PreferenceSwitch pref) {
        // pref.setTitle("Title");
        // pref.setDescription("Description");
        // if use icon : pref.setIcon(R.drawable.your_image);
        pref.setSwitchChangedListener((buttonView, isChecked) -> { 
            // your action
        });
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
}
