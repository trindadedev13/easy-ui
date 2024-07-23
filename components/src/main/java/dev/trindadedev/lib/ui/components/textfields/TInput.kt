package dev.trindadedev.lib.ui.components.textfields

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.text.Editable
import android.text.TextUtils

import androidx.core.content.ContextCompat

import com.google.android.material.textfield.TextInputEditText

import dev.trindadedev.lib.R
import dev.trindadedev.lib.databinding.LayoutTinputTextfieldBinding

class TInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutTinputTextfieldBinding = LayoutTinputTextfieldBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TInput, defStyleAttr, 0)
        
        binding.background.hint = typedArray.getString(R.styleable.TInput_hint)
        binding.background.placeholderText = typedArray.getString(R.styleable.TInput_placeholderText)
        
        val textString: String? = typedArray.getString(R.styleable.TInput_text)
        val editableText: Editable? = if (!TextUtils.isEmpty(textString)) {
            Editable.Factory.getInstance().newEditable(textString)
        } else {
            null
        }
        binding.edittext.text = editableText
        
        val startIconDrawableRes = typedArray.getResourceId(R.styleable.TInput_startIconDrawable, 0)
        if (startIconDrawableRes != 0) {
            binding.background.startIconDrawable = ContextCompat.getDrawable(context, startIconDrawableRes)
        }
        
        typedArray.recycle()
    }

    var hint: CharSequence?
        get() = binding.background.hint
        set(value) {
            binding.background.hint = value
        }

    var placeholderText: CharSequence?
        get() = binding.background.placeholderText
        set(value) {
            binding.background.placeholderText = value
        }

    var startIconDrawableRes: Int
        get() = 0
        set(value) {
            binding.background.startIconDrawable = ContextCompat.getDrawable(context, value)
        }

    var text: CharSequence?
        get() = binding.edittext.text
        set(value) {
            binding.edittext.setText(value)
        }
}