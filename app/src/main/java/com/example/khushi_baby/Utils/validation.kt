package com.example.khushi_baby.Utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class validation(private val context: Context) {
    fun isInputEditTextEmail(textInputEditText: EditText): Boolean {
        val value = textInputEditText.text.toString().trim()
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            hideKeyboardFrom(textInputEditText)
            return false
        } else {
            return true
        }
    }

    fun phoneNumberValidation(textInputEditText: EditText): Boolean{
        val value = textInputEditText.text.toString().trim()
        if (value.isEmpty() || value.length!=10) {
            hideKeyboardFrom(textInputEditText)
            return false
        } else {
            return true
        }
    }

    private fun hideKeyboardFrom(view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}