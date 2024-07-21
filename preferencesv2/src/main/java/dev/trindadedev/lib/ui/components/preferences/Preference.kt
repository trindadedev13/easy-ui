package dev.trindadedev.lib.ui.components.preferences

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import dev.trindadedev.lib.R

class Preference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val preferenceTitle: TextView
    private val preferenceDescription: TextView
    private val preference: View

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_preference, this, true)

        preferenceTitle = findViewById(R.id.preferenceTitle)
        preferenceDescription = findViewById(R.id.preferenceDescription)
        preference = findViewById(R.id.preference)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Preference,
            0, 0
        ).apply {
            try {
                val title = getString(R.styleable.Preference_preferenceTitle) ?: ""
                val description = getString(R.styleable.Preference_preferenceDescription) ?: ""
                preferenceTitle.text = title
                preferenceDescription.text = description
            } finally {
                recycle()
            }
        }
    }

    fun setPreferenceClickListener(listenerClick: View.OnClickListener) {
        preference.setOnClickListener(listenerClick)
    }
    
    fun setTitle(value: String) {
         preferenceTitle.text = value
    }
    
    fun setDescription(value: String) {
         preferenceDescription.text = value
    }
}