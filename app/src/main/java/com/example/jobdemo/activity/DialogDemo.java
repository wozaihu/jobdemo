package com.example.jobdemo.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.dialog.VerificationDialogFragment;
import com.tencent.bugly.crashreport.CrashReport;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogDemo extends BaseActivity {
    @BindView(R.id.tv_show_dialog_fragment)
    TextView tvShowDialogFragment;
    @BindView(R.id.tv_show_dialog_fragment2)
    TextView tvShowDialogFragment2;
    private AlertDialog circularDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_demo);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_show_dialog_fragment, R.id.tv_show_dialog_fragment2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_show_dialog_fragment:
                new VerificationDialogFragment().show(getSupportFragmentManager(), "");
                break;
            case R.id.tv_show_dialog_fragment2:
                shwoCircularDialog("请注意", "确定支付后，资金将直接");
                break;
        }
    }

    private void shwoCircularDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_default, null);
        TextView tvConfirm;
        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvMessage = view.findViewById(R.id.tv_message);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);
        builder.setView(view);
        tvTitle.setText(title);
        tvMessage.setText(message);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularDialog != null) {
                    circularDialog.dismiss();
                }
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularDialog != null) {
                    Toast.makeText(DialogDemo.this, "点击了确定", Toast.LENGTH_LONG).show();
                    circularDialog.dismiss();
                }
            }
        });
        circularDialog = builder.create();
        circularDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        circularDialog.show();
    }
}
