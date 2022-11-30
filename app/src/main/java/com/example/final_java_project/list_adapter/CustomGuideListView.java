package com.example.final_java_project.list_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.final_java_project.R;

import java.util.ArrayList;

public class CustomGuideListView extends BaseAdapter
{
    LayoutInflater layoutInflater = null;
    private ArrayList<ListData> listViewData = null;
    private int count = 0;

    public CustomGuideListView(ArrayList<ListData> listData)
    {
        listViewData = listData;
        count = listViewData.size();
    }

    @Override
    public int getCount()
    {
        return count;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            final Context context = parent.getContext();
            if (layoutInflater == null)
            {
                layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = layoutInflater.inflate(R.layout.guide_custom_listview, parent, false);
        }
        TextView title = convertView.findViewById(R.id.title);
        TextView body_2 = convertView.findViewById(R.id.body_2);
        title.setText(listViewData.get(position).title);
        body_2.setText(listViewData.get(position).body_2);
        return convertView;
    }
    public static class ListData {
        public String nameId = "";
        public String title = "";
        public String body_2 = "";
    }
}
