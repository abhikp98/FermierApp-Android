package com.app.fermierapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomViewRequestStatus extends BaseAdapter {
    String[] date, time, status, officer;
    Context context;

    public CustomViewRequestStatus(Context applicationContext, String[] date, String[] time, String[] status, String[] officer) {
        this.context = applicationContext;
        this.date = date;
        this.time = time;
        this.status = status;
        this.officer = officer;
    }


    @Override
    public int getCount() {
        return date.length;
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
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertView==null)
        {
            gridView=inflator.inflate(R.layout.activity_custom_view_request_status,null);

        }
        else
        {
            gridView=(View)convertView;

        }
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        TextView n=(TextView)gridView.findViewById(R.id.textView22);
        TextView d=(TextView)gridView.findViewById(R.id.textView20);
        TextView dt=(TextView)gridView.findViewById(R.id.textView23);
        TextView t=(TextView)gridView.findViewById(R.id.textView21);

        n.setText(officer[position]);
        d.setText(status[position]);
        dt.setText(date[position]);
        t.setText(time[position]);
        return gridView;
    }
}