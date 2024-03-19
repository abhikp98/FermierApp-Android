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
import android.widget.TextView;

public class CustomViewItems extends BaseAdapter {
    String[] name, quantity, price;
    Context context;
    public CustomViewItems(Context applicationContext, String[] name, String[] quantity, String[] price) {
        this.name = name;
        this.context = applicationContext;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public int getCount() {
        return name.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_items,null);

        }
        else
        {
            gridView=(View)convertView;

        }
        TextView n=(TextView)gridView.findViewById(R.id.textView25);
        TextView q=(TextView)gridView.findViewById(R.id.textView24);
        TextView amt=(TextView)gridView.findViewById(R.id.textView26);

        n.setText(name[position]);
        q.setText(quantity[position]);
        amt.setText(price[position]);
        return gridView;
    }
}