package com.example.jobdemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.jobdemo.R;
import com.example.jobdemo.bean.TestBean;

import java.util.List;

/**
 * @author Administrator
 */
public class SpinnerAdapter extends BaseAdapter {
    private List<TestBean> list;
    private Context context;

    public SpinnerAdapter(Context context, List<TestBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText("姓名：" + list.get(position).getName() + "---性别：" + list.get(position).getGrander() + "---年龄：" + list.get(position).getAge());
        if (list.get(position).isSelect()) {
            holder.textView.setTextColor(ContextCompat.getColor(context, R.color.yellow));
        } else {
            holder.textView.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
        return convertView;
    }

    class ViewHolder {
        private TextView textView;

        public ViewHolder(View view) {
            textView = view.findViewById(android.R.id.text1);
        }
    }
}
