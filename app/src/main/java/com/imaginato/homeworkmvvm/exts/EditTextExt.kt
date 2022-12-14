package com.imaginato.homeworkmvvm.exts

import android.text.Editable
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText

fun TextInputEditText.getTextAfterChanged(onTextChanges: (text: String) -> Unit) {
    addTextChangedListener { text: Editable? ->
        text?.let { onTextChanges(it.toString()) }
    }
}