package com.example.don.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class adapter_Helper extends ArrayAdapter<adapter_class> {

    private static final String TAG = "adapterHelper";

    private Context context;
    int Resource;

    public adapter_Helper(Context context, int resource,ArrayList<adapter_class> objects) {
        super(context, resource, objects);
        this.context = context;
        Resource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        String name = getItem(position).getName();
        int initial = getItem(position).getInitial();

        adapter_class person = new adapter_class(name,initial);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(Resource,parent,false);

        TextView tvName = (TextView) convertView.findViewById(R.id.col1);
        TextView tvInitial = (TextView) convertView.findViewById(R.id.col2);

        tvName.setText(name);
        tvInitial.setText(String.valueOf(initial));

        return convertView;

    }
}
