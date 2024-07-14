package dev.trindadeaquiles.ui.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.google.android.material.materialswitch.MaterialSwitch;

import dev.trindadeaquiles.R;
import dev.trindadeaquiles.databinding.LayoutPreferenceSwitchBinding;

public class PreferenceSwitch extends RelativeLayout implements View.OnClickListener {

    private boolean value = false;
    private LayoutPreferenceSwitchBinding binding;

    public PreferenceSwitch(Context context) {
        super(context);
        initialize(context, null);
    }

    public PreferenceSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public PreferenceSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs) {
        binding = LayoutPreferenceSwitchBinding.inflate(LayoutInflater.from(context), this, true);
        setOnClickListener(this);

        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PreferenceSwitch,
                0, 0);

            try {
                String title = a.getString(R.styleable.PreferenceSwitch_preferenceSwitchTitle);
                String description = a.getString(R.styleable.PreferenceSwitch_preferenceSwitchDescription);
                boolean defaultValue = a.getBoolean(R.styleable.PreferenceSwitch_preferenceSwitchDefaultValue, false);

                setTitle(title != null ? title : "");
                setDescription(description != null ? description : "");
                setValue(defaultValue);
            } finally {
                a.recycle();
            }
        }
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
        binding.preferenceSwitch.setChecked(value);
    }

    @Override
    public void onClick(View view) {
        setValue(!value);
    }

    public void setDescription(String value) {
        binding.preferenceDescription.setText(value);
    }

    public void setTitle(String value) {
        binding.preferenceName.setText(value);
    }

    public void setSwitchChangedListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        binding.preferenceSwitch.setOnCheckedChangeListener(onCheckedChangeListener);
    }
}