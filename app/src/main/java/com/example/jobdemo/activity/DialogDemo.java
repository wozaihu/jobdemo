package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.constants.Api;
import com.example.jobdemo.databinding.DialogDemoBinding;
import com.example.jobdemo.dialog.VerificationDialogFragment;

/**
 * @author Administrator
 */
public class DialogDemo extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DialogDemoBinding binding = DialogDemoBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        binding.tvShowDialog.setOnClickListener(v ->
                new VerificationDialogFragment().show(getSupportFragmentManager(), ""));
        binding.tvChangeDefaultTip.setOnClickListener(v-> Api.DEFAULT_TIP_STRING=getString( R.string.changeTip));
    }
}
