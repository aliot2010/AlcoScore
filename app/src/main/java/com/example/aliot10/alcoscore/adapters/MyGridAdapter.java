package com.example.aliot10.alcoscore.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.aliot10.alcoscore.R;

/**
 * Created by aliot on 31.10.2016.
 */

public class MyGridAdapter extends ArrayAdapter {
    Integer[] integers;
    public MyGridAdapter(Context context, int resource, Integer[] integers) {

        super(context, R.layout.griditem, integers);
        this.integers = integers;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.griditem, null);
        }
        ((ImageView)convertView.findViewById(R.id.imageViewInCardView))
                .setImageResource(integers[position]);
        return convertView;
    }
}
