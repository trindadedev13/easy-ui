package dev.trindadedev.lib.ui.components.textfield

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.text.Editable
import android.text.TextUtils
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.trindadedev.lib.ui.components.R

class TInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val textInputLayout: TextInputLayout
    private val textInputEditText: TextInputEditText

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_tinput_textfield, this, true)
        textInputLayout = view.findViewById(R.id.text_input_layout)
        textInputEditText = view.findViewById(R.id.text_input_edittext)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TInput, defStyleAttr, 0)

        textInputLayout.hint = typedArray.getString(R.styleable.TInput_hint)
        textInputLayout.placeholderText = typedArray.getString(R.styleable.TInput_placeholderText)

        val textString: String? = typedArray.getString(R.styleable.TInput_text)
        val editableText: Editable? = if (!TextUtils.isEmpty(textString)) {
            Editable.Factory.getInstance().newEditable(textString)
        } else {
            null
        }
        textInputEditText.text = editableText

        val startIconDrawableRes = typedArray.getResourceId(R.styleable.TInput_startIconDrawable, 0)
        if (startIconDrawableRes != 0) {
            textInputLayout.startIconDrawable = ContextCompat.getDrawable(context, startIconDrawableRes)
        }

        typedArray.recycle()
    }

    var hint: CharSequence?
        get() = textInputLayout.hint
        set(value) {
            textInputLayout.hint = value
        }

    var placeholderText: CharSequence?
        get() = textInputLayout.placeholderText
        set(value) {
            textInputLayout.placeholderText = value
        }

    var startIconDrawableRes: Int
        get() = 0
        set(value) {
            textInputLayout.startIconDrawable = ContextCompat.getDrawable(context, value)
        }

    var text: CharSequence?
        get() = textInputEditText.text
        set(value) {
            textInputEditText.setText(value)
        }
}