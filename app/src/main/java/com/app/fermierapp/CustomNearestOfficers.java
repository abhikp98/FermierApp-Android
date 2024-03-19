package com.app.fermierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CustomNearestOfficers extends BaseAdapter {
    String[] offname, email, phone, distance;
    Context context;

    public CustomNearestOfficers(Context applicationContext, String[] offname, String[] email, String[] phone, String[] distance) {
        this.context = applicationContext;
        this.offname = offname;
        this.email = email;
        this.phone = phone;
        this.distance = distance;
    }


    @Override
    public int getCount() {
        return offname.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_nearest_officers,null);

        }
        else
        {
            gridView=(View)convertView;

        }
        TextView n=(TextView)gridView.findViewById(R.id.textView12);
        TextView d=(TextView)gridView.findViewById(R.id.textView14);
        TextView t=(TextView)gridView.findViewById(R.id.textView15);
        TextView p=(TextView)gridView.findViewById(R.id.textView13);

        n.setText(offname[position]);
        d.setText(email[position]);
        t.setText(phone[position]);
        p.setText(distance[position]+" Km");
        return gridView;
    }
}