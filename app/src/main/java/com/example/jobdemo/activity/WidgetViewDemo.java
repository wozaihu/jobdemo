package com.example.jobdemo.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.jobdemo.R;
import com.example.jobdemo.R;
import com.example.jobdemo.adapter.SpinnerAdapter;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.bean.TestBean;
import com.example.jobdemo.util.RandomNameUtil;
import com.example.jobdemo.view.SpinnerView;
import com.example.jobdemo.widget.VerCodeInputView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WidgetViewDemo extends BaseActivity implements SpinnerView.ItemClickBackCall {
    @BindView(R.id.vcd)
    VerCodeInputView vcd;
    @BindView(R.id.btn_showDialog)
    Button btnShowDialog;
    @BindView(R.id.spv_1)
    SpinnerView spv1;
    private List<TestBean> list = new ArrayList();
    private SpinnerAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgetviewdemo);
        ButterKnife.bind(this);
        spv1.setHint("请选择");
        spv1.setRootViewBackground(R.drawable.rectangle_bg);
        spv1.setIcon(R.drawable.selector_spinner);
        spv1.setWay(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        for (int i = 1; i <= 15; i++) {
            if (i % 2 == 0) {
                list.add(new TestBean(RandomNameUtil.randomName(true, 3), i, "女"));
            } else {
                list.add(new TestBean(RandomNameUtil.randomName(true, 3), i, "男"));
            }
        }

        listView = spv1.getListView();
        adapter = new SpinnerAdapter(this, list);
        spv1.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            list.get(position).setSelect(true);
            spv1.setHint(list.get(position).getName());
            if (spv1.getDefaultSelectorPosition() != -2 && spv1.getDefaultSelectorPosition() < list.size()) {
                list.get(spv1.getDefaultSelectorPosition()).setSelect(false);   //把上一个选中的取消
            }
            spv1.setDefaultSelectorPosition(position);
            spv1.popupWindowDismiss();
            adapter.notifyDataSetChanged();
        });
    }

    @OnClick({R.id.btn_showDialog, R.id.btn_showSpinnerView})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.btn_showDialog) {
            showDialog();
        } else if (id == R.id.btn_showSpinnerView) {
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_widgetviewdemo, null);
        builder.setView(view);
        AlertDialog dialog = builder.show();
    }

    /**
     * @param position 点击的位置
     * @param tag      判断是哪个spinnerView的记号
     */
    @Override
    public void itemClick(int position, int tag) {
        if (tag == 159) {
            Toast.makeText(this, "点击了第一个spinnerView", Toast.LENGTH_SHORT).show();
        }
    }
}
