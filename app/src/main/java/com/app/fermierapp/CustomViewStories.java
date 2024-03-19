package com.app.fermierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CustomViewStories extends BaseAdapter {
    String[] image, description, date, time;
    Context context;

    public CustomViewStories(Context applicationContext, String[] image, String[] description, String[] date, String[] time) {
        this.context = applicationContext;
        this.image = image;
        this.description = description;
        this.date = date;
        this.time = time;
    }


    @Override
    public int getCount() {
        return image.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_stories,null);

        }
        else
        {
            gridView=(View)convertView;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView6);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView7);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView27);
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView3);

        tv1.setTextColor(Color.BLACK);


        tv1.setText(description[position]);
        tv2.setText(date[position]);
        tv3.setText(time[position]);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("url","");

        String url=ip+image[position];


        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);

        return gridView;
    }
}