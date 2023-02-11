package com.example.jobdemo.kotlin_code.activity

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.jobdemo.databinding.ShapeAppearanceDemoBinding
import com.google.android.material.shape.*

/**

 * @Author LYX

 * @Date 2023/2/3 15:54

 */
class ShapeAppearanceDemo : AppCompatActivity() {
    lateinit var binding: ShapeAppearanceDemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShapeAppearanceDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setChatBubble()
    }

    private fun setChatBubble() {
        val shapePathModel = ShapeAppearanceModel.builder()
            .setAllCorners(RoundedCornerTreatment())
            .setAllCornerSizes(16F)
            .setRightEdge(object : TriangleEdgeTreatment(20f, false) {})
            .build()
        val backgroundDrawable = MaterialShapeDrawable(shapePathModel).apply {
            setTint(Color.parseColor("#bebebe"))
            paintStyle = Paint.Style.FILL
        }
        (binding.tvBubble.parent as? ViewGroup)?.clipChildren = false
        binding.tvBubble.background = backgroundDrawable
    }
}