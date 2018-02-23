package com.arsabi.ishtech.irnelectionreportingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arsabi.ishtech.irnelectionreportingapp.R;
import com.arsabi.ishtech.irnelectionreportingapp.ResultActivity;

import java.util.ArrayList;

/**
 * Created by Isho on 2/10/2018.
 */

public class listAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> list = new ArrayList<>();

    public listAdapter(Context context){
        this.context = context;
        list.add("Presidential");
        list.add("Parliamentary");
        list.add("Mayorship / Chairman");
        list.add("Councilor");
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list,parent,false);
        }else {
            view = convertView;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(position){
                    case 0:
                        context.startActivity(new Intent(context, ResultActivity.class).putExtra("categories","Presidential"));
                        break;
                    case 1:
                        context.startActivity(new Intent(context, ResultActivity.class).putExtra("categories","Parliamentary"));
                        break;
                    case 2:
                        context.startActivity(new Intent(context, ResultActivity.class).putExtra("categories","Mayorship"));
                        break;
                    case 3:
                        context.startActivity(new Intent(context, ResultActivity.class).putExtra("categories","Councilor"));
                        break;
                }
            }
        });
        TextView textView = (TextView) view.findViewById(R.id.name);
        textView.setText(list.get(position));
        return view;
    }

}
