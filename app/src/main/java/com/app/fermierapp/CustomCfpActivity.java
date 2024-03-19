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

public class CustomCfpActivity extends BaseAdapter {
    Context context;
    String[] cfpname, description, photo, type, price;

    public CustomCfpActivity(Context applicationContext, String[] cfpname, String[] description, String[] photo, String[] type, String[] price) {
    this.context = applicationContext;
    this.cfpname = cfpname;
    this.description = description;
    this.photo = photo;
    this.type = type;
    this.price = price;
    }

    @Override
    public int getCount() {
        return cfpname.length;
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
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.activity_custom_cfp,null);

        }
        else
        {
            gridView=(View)convertView;

        }
        TextView n=(TextView)gridView.findViewById(R.id.textView8);
        TextView d=(TextView)gridView.findViewById(R.id.textView9);
        TextView t=(TextView)gridView.findViewById(R.id.textView11);
        TextView p=(TextView)gridView.findViewById(R.id.textView10);

        ImageView im=(ImageView) gridView.findViewById(R.id.imageView2);


        n.setText(cfpname[position]);
        d.setText(description[position]);
        t.setText(type[position]);
        p.setText(price[position]+" ₹");


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String url=sh.getString("url", "")+photo[position];

        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);

        return gridView;
    }
}