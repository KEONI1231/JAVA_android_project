package com.example.final_java_project.list_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.final_java_project.R;

import java.util.ArrayList;

public class CustomChatView extends BaseAdapter {
    LayoutInflater layoutInflater = null;
    private ArrayList<ListData> listViewData = null;
    private int count = 0;

    public CustomChatView(ArrayList<ListData> listData) {
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
        convertView = layoutInflater.inflate(R.layout.chat_custom_listview, parent, false);
        LinearLayout titleLayout = convertView.findViewById(R.id.item_title_layout);
        ConstraintLayout toplayout = convertView.findViewById(R.id.gravity_set);
        ConstraintSet constraintSet;

        //mainImage.setImageResource(listViewData.get(position).mainImage);

        if (listViewData.get(position).title.equals("나")) {
            constraintSet = new ConstraintSet();
            constraintSet.clone(toplayout);
            constraintSet.connect(toplayout.getId(), ConstraintSet.RIGHT, titleLayout.getId(), ConstraintSet.RIGHT, 0);
            constraintSet.applyTo(toplayout);
        } else {
            constraintSet = new ConstraintSet();
            constraintSet.clone(toplayout);
            constraintSet.connect(titleLayout.getId(), ConstraintSet.LEFT, titleLayout.getId(), ConstraintSet.LEFT, 0);
            constraintSet.applyTo(toplayout);
        }


        //ImageView mainImage = convertView.findViewById(R.id.mainImage);
        TextView title = convertView.findViewById(R.id.title1111);
        TextView body_1 = convertView.findViewById(R.id.body_1);

        title.setText(listViewData.get(position).title);
        body_1.setText(listViewData.get(position).body_1);

        return convertView;
    }

    public static class ListData {
        public int mainImage = 0;
        public String title = "";
        public String body_1 = "";

    }
}
