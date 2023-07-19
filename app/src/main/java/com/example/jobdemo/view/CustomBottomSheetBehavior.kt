package com.example.jobdemo.view

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior

class CustomBottomSheetBehavior<V : View> : BottomSheetBehavior<V>() {

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: V,
        dependency: View
    ): Boolean {
        ViewCompat.offsetTopAndBottom(child, -dependency.y.toInt())
        return super.onDependentViewChanged(parent, child, dependency)
    }
}