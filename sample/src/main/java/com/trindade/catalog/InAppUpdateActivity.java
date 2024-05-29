package com.trindade.;

import android.content.res.Resources;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import com.trindade.R;
import com.trindade.Logger;
import com.trindade.BaseActivity;
import com.trindade.databinding.ActivityMainBinding;

public class InAppUpdateActivity extends BaseActivity {

    private ActivityInAppUpdateBinding binding;
    private Resources a;
    
    private InAppUpdate inApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInAppUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        try{
            a = getResources();
            inApp = new InAppUpdate(this, getResources().getString(R.string.mobisoft_api_key));
            
            findViewById(R.id.viewLogs).setOnClickListener(v -> {
                MaterialAlertDialogBuilder logsDialog = new MaterialAlertDialogBuilder(this);
                logsDialog.setTitle("Logs");
                logsDialog.setMessage(inApp.getLogs());
                logsDialog.setPositiveButton("OK", (d, w) -> {
                }).show();
            });
            
            new Handler().postDelayed(new Runnable() {
    	     @Override
      	     public void run(){
                   init();
               } 
            } ,2500);
        } catch (Exception e) {
            showMessage(e.toString());
        }
    }
    
    
    private void init () {
        if(inApp.isExistUpdate()) {
            MaterialAlertDialogBuilder updDialog = new MaterialAlertDialogBuilder(this);
            updDialog.setTitle(a.getString(R.string.upd_title) + inApp.getVersion());
            updDialog.setMessage(a.getString(R.string.upd_message) + inApp.getNews());
            updDialog.setPositiveButton(a.getString(R.string.upd_ok), (d, w) -> {
                    inApp.downloadUpdate(inApp.getUrl(), a.getString(R.string.app_name));
            }).show();
        } else {
            showMessage(a.getString(R.string.no_update));
        }
    }
}
