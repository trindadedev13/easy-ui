package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dev.trindadedev.lib.filepicker.model.DialogConfigs;
import dev.trindadedev.lib.filepicker.model.DialogProperties;
import dev.trindadedev.lib.filepicker.view.FilePickerDialog;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.showFp.setOnClickListener(v-> showFilePicker());
    }

    void showFilePicker() {
        DialogProperties properties = new DialogProperties();
        properties.selection_mode = DialogConfigs.MULTI_MODE;
        properties.selection_type = DialogConfigs.FILE_SELECT; 
        properties.extensions = new String[]{".java"};

        FilePickerDialog filePickerDialog = new FilePickerDialog(this, properties);
        filePickerDialog.setTitle("Select file");
        filePickerDialog.setDialogSelectionListener(files -> {
            if (files != null && files.length > 0) {
                StringBuilder fileNames = new StringBuilder();
                for (String file : files) {
                    fileNames.append(file.substring(file.lastIndexOf('/') + 1)).append("\n");
                }
                Toast.makeText(this, "Selected file(s):\n" + fileNames.toString().trim(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "No files selected", Toast.LENGTH_SHORT).show();
            }
        });
        filePickerDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
}