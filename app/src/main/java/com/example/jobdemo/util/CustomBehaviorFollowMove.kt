package com.example.jobdemo.util

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
import com.example.jobdemo.R

class CustomBehaviorFollowMove(context: Context?, attrs: AttributeSet?) :
    Behavior<View>(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        if (dependency.id == R.id.greenView) {
            return true
        }
        return super.layoutDependsOn(parent, child, dependency)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        val left = dependency.x
        val screenWidth = parent.width
        child.x = screenWidth - left - child.width
        child.y = dependency.y
        return super.onDependentViewChanged(parent, child, dependency)
    }
}