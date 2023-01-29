package com.example.jobdemo.kotlin_code.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobdemo.R
import com.example.jobdemo.databinding.LayoutBottomDialogBinding
import com.example.jobdemo.kotlin_code.adapter.BottomDialogAdapter
import com.example.jobdemo.util.ToastUtils
import com.example.jobdemo.util.Utils
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.divider.MaterialDividerItemDecoration

/**

 * @Author LYX

 * @Date 2023/1/2 17:53

 */
class BottomDialog : BottomSheetDialogFragment() {
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val binding = LayoutBottomDialogBinding.inflate(inflater, container, false)
//        val defaultSentenceArray: Array<String> = if (Utils.getUserType(context)) {
//            resources.getStringArray(R.array.companyOftenSentence)
//        } else {
//            resources.getStringArray(R.array.normalOftenSentence)
//        }
//        val list: MutableList<String> = ArrayList()
//        list.addAll(defaultSentenceArray)
//        binding.rvSentenceList.layoutManager = LinearLayoutManager(context)
//        context?.let {
//            val decoration =
//                MaterialDividerItemDecoration(it, MaterialDividerItemDecoration.VERTICAL)
//            decoration.dividerColor = ContextCompat.getColor(it, R.color.lightGray)
//            binding.rvSentenceList.addItemDecoration(decoration)
//        }
//        binding.rvSentenceList.adapter = BottomDialogAdapter(list)
//        binding.tvAdd.setOnClickListener { ToastUtils.shortToast(context, "点击了添加") }
//        binding.tvRevamp.setOnClickListener { ToastUtils.shortToast(context, "点击了修改") }
//        return binding.root
//    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = LayoutBottomDialogBinding.inflate(layoutInflater)
        val defaultSentenceArray: Array<String> = if (Utils.getUserType(context)) {
            resources.getStringArray(R.array.companyOftenSentence)
        } else {
            resources.getStringArray(R.array.pictureUrls)
        }
        val list: MutableList<String> = ArrayList()
        list.addAll(defaultSentenceArray)
        binding.rvSentenceList.layoutManager = LinearLayoutManager(context)
        context?.let {
            val decoration =
                MaterialDividerItemDecoration(it, MaterialDividerItemDecoration.VERTICAL)
            decoration.dividerColor = ContextCompat.getColor(it, R.color.lightGray)
            binding.rvSentenceList.addItemDecoration(decoration)
        }
        binding.rvSentenceList.adapter = BottomDialogAdapter(list)
        binding.tvAdd.setOnClickListener { ToastUtils.shortToast(context, "点击了添加") }
        binding.tvRevamp.setOnClickListener { ToastUtils.shortToast(context, "点击了修改") }


        val root: View = binding.root
        //这样创建dialog就这样传递style
        val dialog = BottomSheetDialog(requireContext(), R.style.TransBottomSheetDialogStyle)
        dialog.setContentView(root);
        //设置宽度
        val params: ViewGroup.LayoutParams = root.layoutParams
        params.height = (0.75 * resources.displayMetrics.heightPixels).toInt()
        root.layoutParams = params
        return dialog
    }

//    override fun getTheme(): Int {
//        return R.style.TransBottomSheetDialogStyle
//    }
}