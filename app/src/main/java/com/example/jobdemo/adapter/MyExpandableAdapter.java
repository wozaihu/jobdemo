package com.example.jobdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jobdemo.R;
import com.example.jobdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyExpandableAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private String[] titles;
    String[][] contents;

    public MyExpandableAdapter(Context mContext, String[] titles, String[][] contents) {
        this.mContext = mContext;
        this.titles = titles;
        this.contents = contents;
    }

    @Override
    public int getGroupCount() {
        return titles.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return contents[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder groupHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_expandable_group, parent, false);
            groupHolder = new ViewHolder(convertView);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (ViewHolder) convertView.getTag();
        }
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(groupHolder.tvTitle.getLayoutParams());
//        params.setMargins(150,0,0,0);
//        params.leftMargin=150;
//        params.addRule(RelativeLayout.CENTER_VERTICAL);
//        groupHolder.tvTitle.setLayoutParams(params);
        groupHolder.tvTitle.setText(titles[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemViewHolder itemHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_expandable_child, parent, false);
            itemHolder = new ItemViewHolder(convertView);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ItemViewHolder) convertView.getTag();
        }
        itemHolder.tvChildContent.setText(contents[groupPosition][childPosition]);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    static class ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_group_indicator)
        ImageView ivGroupIndicator;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ItemViewHolder {
        @BindView(R.id.tv_child_content)
        TextView tvChildContent;

        ItemViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
