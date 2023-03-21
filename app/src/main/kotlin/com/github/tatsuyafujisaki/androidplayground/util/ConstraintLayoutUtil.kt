package com.github.tatsuyafujisaki.androidplayground.util

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams

object ConstraintLayoutUtil {
    private fun constraintLayoutExample(cl: ConstraintLayout) {
        cl.updateLayoutParams<ConstraintLayout.LayoutParams> {
            width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
            height = ViewGroup.LayoutParams.WRAP_CONTENT
            // bottomToTop = R.id.my_view
        }
    }
}
