package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.dialog.VerificationDialogFragment;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogDemo extends BaseActivity {
    @BindView(R.id.tv_show_dialog_fragment)
    TextView tvShowDialogFragment;
    @BindView(R.id.tv_show_dialog_fragment2)
    TextView tvShowDialogFragment2;

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
                new VerificationDialogFragment().show(getSupportFragmentManager(),"");
                break;
            case R.id.tv_show_dialog_fragment2:
                break;
        }
    }
}
