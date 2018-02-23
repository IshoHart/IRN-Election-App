package com.arsabi.ishtech.irnelectionreportingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arsabi.ishtech.irnelectionreportingapp.R;

import java.util.ArrayList;

/**
 * Created by Isho on 2/10/2018.
 */

public class Sort extends BaseAdapter {

    private Context context;
    private String[] list;

    public Sort(Context context, String[] list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return list[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sort,parent,false);
        }else {
            view = convertView;
        }
        TextView textView = (TextView) view.findViewById(R.id.name);
        textView.setText(list[position]);
        return view;
    }
}
