package com.example.jobdemo.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.jobdemo.R;
import com.example.jobdemo.databinding.UpdatadialogBinding;
import com.example.jobdemo.util.ToastUtils;

import org.jetbrains.annotations.NotNull;

/**
 * @Author LYX
 * @Date 2021/12/30 16:56
 * 两种创建方式：
 * 1、onCreateView直接返回布局，适合创建复杂的dialog，大小由4布局文件决定。
 * 2、onCreateDialog直接返回一个dialog，出来就是默认的dialog大小
 */
public class UpdateDialog extends AppCompatDialogFragment {

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        dialog.setCanceledOnTouchOutside(false);
        UpdatadialogBinding inflate = UpdatadialogBinding.inflate(getLayoutInflater());
        inflate.cancel.setText(getString(R.string.cancel));
        inflate.confirmDownload.setText(getString(R.string.confirmDownload));
        inflate.message.setText(getString(R.string.toolbar_subtitle));
        inflate.cancel.setOnClickListener(v -> dismiss());
        inflate.confirmDownload.setOnClickListener(v ->
                ToastUtils.shortToast("点击了确定下载")
        );
        return inflate.getRoot();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
