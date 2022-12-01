package com.example.final_java_project.list_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.final_java_project.R;

import java.util.ArrayList;
import java.util.Locale;

public class CustomSearchListView extends BaseAdapter {
    LayoutInflater layoutInflater = null;
    private ArrayList<ListData> listViewData = null;
    private int count = 0;

    public CustomSearchListView(ArrayList<ListData> listData) {
        listViewData = listData;
        count = listViewData.size();

    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            final Context context = parent.getContext();
            if (layoutInflater == null) {
                layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            } else {
            }
        }
        convertView = layoutInflater.inflate(R.layout.search_custom_listview, parent, false);

        TextView body_1 = convertView.findViewById(R.id.body_1);
        body_1.setText(listViewData.get(position).body_1);
        return convertView;
    }
    public static class ListData {
        public String body_1 = "";
    }


}
