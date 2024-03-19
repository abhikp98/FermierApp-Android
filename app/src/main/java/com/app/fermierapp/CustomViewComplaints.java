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

public class CustomViewComplaints extends BaseAdapter {
    Context context;
    String[] complaint, reply, replydate, cdate;

    public CustomViewComplaints(Context applicationContext, String[] complaint, String[] cdate, String[] reply, String[] replydate) {

        this.context = applicationContext;
        this.complaint = complaint;
        this.reply = reply;
        this.cdate = cdate;
        this.replydate = replydate;
    }

    @Override
    public int getCount() {
        return complaint.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_complaints,null);

        }
        else
        {
            gridView=(View)convertView;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView2);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView3);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView4);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView5);



        tv1.setText(complaint[position]);
        tv2.setText(cdate[position]);
        if(replydate[position].equalsIgnoreCase("pending")){
            tv4.setVisibility(View.INVISIBLE);
            tv3.setText("Wait for Admin to Respond!");
            tv3.setTextColor(Color.RED);
        }
        else {
            tv3.setText(reply[position]);
            tv4.setText(replydate[position]);
        }



        return gridView;
    }
}