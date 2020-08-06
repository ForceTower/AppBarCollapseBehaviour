package dev.forcetower.picpay.core.bindings

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dev.forcetower.picpay.widget.outline.RoundedOutlineProvider

fun View.inflater(): LayoutInflater = LayoutInflater.from(context)

inline fun <reified T : ViewDataBinding> ViewGroup.inflate(@LayoutRes res: Int, attachToParent: Boolean = false): T {
    val inflater = inflater()
    return DataBindingUtil.inflate(inflater, res, this, attachToParent)
}

inline fun <reified T : ViewDataBinding> LayoutInflater.inflate(@LayoutRes res: Int): T {
    return DataBindingUtil.inflate(this, res, null, false)
}

@BindingAdapter("roundedViewRadius")
fun roundedViewRadius(view: View, radius: Int) {
    view.clipToOutline = true
    view.outlineProvider = RoundedOutlineProvider(view.context.getPixelsFromDp(radius))
}

fun Context.getPixelsFromDp(dp: Int): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics)