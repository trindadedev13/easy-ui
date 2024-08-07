package dev.trindadedev.lib.ui.components.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import dev.trindadedev.lib.ui.components.R;

public class PermissionDialog extends Dialog {

    private ImageView dialogIcon;
    private TextView dialogText;
    private LinearLayout buttonAllow;
    private LinearLayout buttonDeny;

    private int iconResId;
    private String text;
    private View.OnClickListener allowClickListener;
    private View.OnClickListener denyClickListener;

    private PermissionDialog(Context context, Builder builder) {
        super(context);
        this.iconResId = builder.iconResId;
        this.text = builder.text;
        this.allowClickListener = builder.allowClickListener;
        this.denyClickListener = builder.denyClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_permission);

        dialogIcon = findViewById(R.id.dialog_icon);
        dialogText = findViewById(R.id.dialog_text);
        buttonAllow = findViewById(R.id.button_allow);
        buttonDeny = findViewById(R.id.button_deny);

        dialogIcon.setImageResource(iconResId);
        dialogText.setText(Html.fromHtml(text));

        buttonAllow.setOnClickListener(v -> {
            if (allowClickListener != null) {
                allowClickListener.onClick(v);
            }
            dismiss();
        });

        buttonDeny.setOnClickListener(v -> {
            if (denyClickListener != null) {
                denyClickListener.onClick(v);
            }
            dismiss();
        });

        if (getWindow() != null) {
            getWindow().getDecorView().setBackgroundColor(0);
        }
        setCancelable(false);
    }

    public static class Builder {
        private final Context context;
        private int iconResId = R.drawable.ic_dot_24;
        private String text = "Default text";
        private View.OnClickListener allowClickListener;
        private View.OnClickListener denyClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setIconResId(@DrawableRes int iconResId) {
            this.iconResId = iconResId;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setAllowClickListener(View.OnClickListener listener) {
            this.allowClickListener = listener;
            return this;
        }

        public Builder setDenyClickListener(View.OnClickListener listener) {
            this.denyClickListener = listener;
            return this;
        }

        public PermissionDialog build() {
            return new PermissionDialog(context, this);
        }
    }
}
