package br.com.brunobrito.covidhack.platform.extension

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.TextView
import androidx.core.animation.doOnEnd


fun View.collapse(duration: Long, end: () -> Unit) {
    val v: View = this
    val scaleAnimation = ScaleAnimation(1F, 1F, 1F, 0F, 0F, 0F)
    scaleAnimation.duration = duration
    scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {}
        override fun onAnimationEnd(animation: Animation) {
            v.visibility = View.GONE
            end()
        }

        override fun onAnimationRepeat(animation: Animation) {}
    })
    v.startAnimation(scaleAnimation)
}

fun View.expand(duration: Long, end: () -> Unit) {
    val v: View = this
    val scaleAnimation = ScaleAnimation(1F, 1F, 0F, 1F, 0F, 0F)
    scaleAnimation.duration = duration
    scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {}
        override fun onAnimationEnd(animation: Animation) {
            v.visibility = View.VISIBLE
            end()
        }

        override fun onAnimationRepeat(animation: Animation) {}
    })
    v.startAnimation(scaleAnimation)
}

fun TextView.showInAdapter() {
    if (this.text.trim().isEmpty()) this.visibility = View.GONE
}

fun View.fadeIn(delay: Long, end: () -> Unit) {
    this.alpha = 0f
    this.visibility = View.VISIBLE
    this.animate().alpha(1f).setDuration(delay).withEndAction {
        end()
    }.start()
}

fun View.fadeOut(delay: Long, end: () -> Unit) {
    this.visibility = View.VISIBLE
    this.animate().alpha(0f).setDuration(delay).withEndAction {
        end()
    }.start()

}
