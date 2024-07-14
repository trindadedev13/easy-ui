package dev.trindadeaquiles.util;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showMessage(String toastText) {
        Toast.makeText(this, toastText, 4000).show();
    }
}
