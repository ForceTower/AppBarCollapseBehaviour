package dev.forcetower.picpay.widget.behavior

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import dev.forcetower.picpay.R
import timber.log.Timber
import kotlin.math.max

class ContactsScrollingBehavior: CoordinatorLayout.Behavior<View> {
    constructor() : super()
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var defaultHeight = 0
    private var measured = false

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: View,
        layoutDirection: Int
    ): Boolean {
        val paramsCompat = child.layoutParams as ViewGroup.MarginLayoutParams
        val height = child.measuredHeight + paramsCompat.bottomMargin

        if (!measured) {
            measured = true
            defaultHeight = child.measuredHeight
            Timber.e("well... changing")
        }

        Timber.d("Child layout with height $height")
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        Timber.d("Dependent view changed ${dependency.id == R.id.app_bar}")
        return super.onDependentViewChanged(parent, child, dependency)
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        consumed[0] = 0

        if (dy > 0) {
            // we scrolling up
            val yUnconsumed = max(dy - child.height, 0)
            val yConsumed = dy - yUnconsumed

            consumed[1] = yConsumed

            child.updateLayoutParams {
                height -= yConsumed
            }

            Timber.d("On pre scroll $dy $yConsumed ------ ${child.measuredHeight} ${child.height}")
        } else {
            consumed[1] = 0
        }
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        if (dyUnconsumed < 0) {
            val leftToConsume = defaultHeight - child.height

            val yUnconsumed = max((dyUnconsumed * -1) - leftToConsume, 0)
            val yConsumed = (dyUnconsumed * -1) - yUnconsumed

            consumed[0] = dxUnconsumed
            consumed[1] = -yConsumed

            child.updateLayoutParams {
                height += yConsumed
            }

            Timber.d(">>>>>>> Left to consume $dyUnconsumed $yConsumed ---------- $leftToConsume ${child.measuredHeight} $defaultHeight")
        } else {
            super.onNestedScroll(
                coordinatorLayout,
                child,
                target,
                dxConsumed,
                dyConsumed,
                dxUnconsumed,
                dyUnconsumed,
                type,
                consumed
            )
        }
    }
}