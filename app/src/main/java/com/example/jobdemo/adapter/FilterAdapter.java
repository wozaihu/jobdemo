package com.example.jobdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Administrator
 * @Date 2021/6/10 13:27
 */
public class FilterAdapter extends BaseAdapter implements Filterable {
    private final Context context;
    private final List<String> list;
    private final List<String> filterList;

    public FilterAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        this.filterList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return filterList.size();
    }

    @Override
    public Object getItem(int position) {
        return filterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(filterList.get(position));
        return convertView;
    }

    static class ViewHolder {
        TextView textView;

        public ViewHolder(View view) {
            textView = view.findViewById(android.R.id.text1);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                String searchValue = constraint.toString().toLowerCase();
                filterList.clear();
                for (String s : list) {
                    if (s.contains(searchValue)) {
                        filterList.add(s);
                    }
                }
                results.values = filterList;
                results.count = filterList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                notifyDataSetChanged();
            }
        };
    }

}
