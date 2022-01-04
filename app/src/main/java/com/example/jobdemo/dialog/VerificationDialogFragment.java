package com.example.jobdemo.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

/**
 * @author Administrator
 */
public class VerificationDialogFragment extends AppCompatDialogFragment {

//    @Nullable
//    @org.jetbrains.annotations.Nullable
//    @Override
//    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
//        Dialog dialog = getDialog();
//        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
//        dialog.setCanceledOnTouchOutside(false);
//        UpdatadialogBinding inflate = UpdatadialogBinding.inflate(getLayoutInflater());
//        inflate.cancel.setText(getString(R.string.cancel));
//        inflate.confirmDownload.setText(getString(R.string.confirmDownload));
//        inflate.message.setText(getString(R.string.toolbar_subtitle));
//        inflate.cancel.setOnClickListener(v -> dismiss());
//        inflate.confirmDownload.setOnClickListener(v ->
//                ToastUtils.shortToast("点击了确定下载")
//        );
//        return inflate.getRoot();
//    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("dialogFragment对话框");
        builder.setMessage("记得今年的目标");
        builder.setPositiveButton("YES",null);
        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "不会忘", Toast.LENGTH_SHORT).show();
                        alertDialog.setMessage("那就好");
                        new CountDownTimer(5000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                dialog.dismiss();
                            }
                        }.start();
                    }
                });
            }
        });
        return alertDialog;
    }
}
