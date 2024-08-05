package dev.trindadedev.lib.ui.components.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import androidx.annotation.DrawableRes

import dev.trindadedev.lib.ui.components.R

class PermissionDialog(
    context: Context,
    @DrawableRes private var iconResId: Int = R.drawable.ic_dot_24,
    private var text: String = "Default text",
    private var allowClickListener: (() -> Unit)? = null,
    private var denyClickListener: (() -> Unit)? = null
) : Dialog(context) {

    private lateinit var dialogIcon: ImageView
    private lateinit var dialogText: TextView
    private lateinit var buttonAllow: LinearLayout
    private lateinit var buttonDeny: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dialog_permission)

        dialogIcon = findViewById(R.id.dialog_icon)
        dialogText = findViewById(R.id.dialog_text)
        buttonAllow = findViewById(R.id.button_allow)
        buttonDeny = findViewById(R.id.button_deny)

        dialogIcon.setImageResource(iconResId)
        dialogText.text = Html.fromHtml(text)

        buttonAllow.setOnClickListener {
            allowClickListener?.invoke()
            dismiss()
        }

        buttonDeny.setOnClickListener {
            denyClickListener?.invoke()
            dismiss()
        }

        window?.decorView?.setBackgroundColor(0)
        setCancelable(false)
    }

    fun setIconResId(@DrawableRes resId: Int) {
        iconResId = resId
        dialogIcon.setImageResource(iconResId)
    }

    fun setText(text: String) {
        this.text = text
        dialogText.text = Html.fromHtml(text)
    }

    fun setAllowClickListener(listener: () -> Unit) {
        allowClickListener = listener
    }

    fun setDenyClickListener(listener: () -> Unit) {
        denyClickListener = listener
    }
}
