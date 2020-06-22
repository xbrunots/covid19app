package br.com.brunobrito.covidhack.platform.extension

import android.animation.ValueAnimator
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView


fun EditText.onKeyText(response: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            response(charSequence.toString())
        }

        override fun afterTextChanged(editable: Editable) {}
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
    })
}

fun TextView.incrementNumber(number: Int) {
    val valueAnimator = ValueAnimator.ofInt(0, number)
    valueAnimator.duration = 2000
    valueAnimator.addUpdateListener { valueAnimator -> this.setText(valueAnimator.animatedValue.toString()) }
    valueAnimator.start()
}