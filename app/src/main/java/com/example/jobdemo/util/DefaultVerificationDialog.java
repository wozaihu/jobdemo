package com.example.jobdemo.util;

import android.app.DialogFragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.jobdemo.R;
import com.example.jobdemo.widget.VerCodeInputView;


/**
 * @author Administrator
 * @date 2020/6/19 16:27
 * @desc
 */
public class DefaultVerificationDialog extends DialogFragment implements View.OnClickListener {
    private static final String TAG = "DefaultDialogFragment";
    private TextView tvPhoneNumberHint;
    private VerCodeInputView viewVerification;
    private Button btn_confirm;
    private TextView tvCountDown;

    public static DefaultVerificationDialog newInstance(Bundle bundle) {
        DefaultVerificationDialog fragment = new DefaultVerificationDialog();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static DefaultVerificationDialog newInstance() {
        DefaultVerificationDialog fragment = new DefaultVerificationDialog();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_verification, container, false);
        tvPhoneNumberHint = (TextView) view.findViewById(R.id.tv_phone_number_hint);
        tvCountDown = (TextView) view.findViewById(R.id.tv_count_down);
        viewVerification = (VerCodeInputView) view.findViewById(R.id.view_verification);
        btn_confirm = (Button) view.findViewById(R.id.btn_confirm);
        tvCountDown.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
        //添加这一行
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        String currentPhone = "13790027422";
        tvPhoneNumberHint.setText("已发送验证码至尾号" + currentPhone.substring(7, 11) + "的手机");
        return view;
    }

    public void setPhonView(String s) {
        if (tvPhoneNumberHint != null) {
            tvPhoneNumberHint.setText(s);
        }
    }

    public void setCountDownView(String countDownTime) {
        if (tvCountDown != null) {
            tvCountDown.setText(countDownTime);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_count_down:
                if (tvCountDown != null) {
                    verificationDialogCall.reGetVerification(tvCountDown); //重新获取验证码
                }
                break;
            case R.id.btn_confirm:
                if (!TextUtils.isEmpty(viewVerification.getEditContent()) && viewVerification.getEditContent().length() == 6) {
                    verificationDialogCall.Confirm(viewVerification.getEditContent());
                }
                break;
        }
    }


    private VerificationDialogCall verificationDialogCall;

    public void setVerificationDialogCall(VerificationDialogCall verificationDialogCall) {
        this.verificationDialogCall = verificationDialogCall;
    }

    public interface VerificationDialogCall {
        void reGetVerification(TextView view);

        void Confirm(String code);

        void fragmentOnDetach();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
