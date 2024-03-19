package com.app.fermierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.function.Predicate;

public class CustomViewNotification extends BaseAdapter {
    String[] notification, date, time, file;
    Context context;
    public CustomViewNotification(Context applicationContext, String[] notification, String[] date, String[] time, String[] file) {
        this.context = applicationContext;
        this.notification = notification;
        this.date = date;
        this.time = time;
        this.file = file;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_notification,null);

        }
        else
        {
            gridView=(View)convertView;

        }
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        TextView n=(TextView)gridView.findViewById(R.id.textView20);
        TextView d=(TextView)gridView.findViewById(R.id.textView22);
        TextView t=(TextView)gridView.findViewById(R.id.textView21);
        Button b=gridView.findViewById(R.id.button5);

        n.setText(notification[position]);
        d.setText(date[position]);
        t.setText(time[position]);
        if (file[position].isEmpty()){
            b.setVisibility(View.INVISIBLE);
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = sh.getString("url", "") + file[position];
                openFile(url);
            }

            private void openFile(String url) {
                Uri uri = Uri.parse(url);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                if (url.contains(".doc") || url.contains(".docx")) {
                    // Word document
                    intent.setDataAndType(uri, "application/msword");
                } else if (url.contains(".pdf")) {
                    // PDF file
                    intent.setDataAndType(uri, "application/pdf");
                } else if (url.contains(".ppt") || url.contains(".pptx")) {
                    // Powerpoint file
                    intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
                } else if (url.contains(".xls") || url.contains(".xlsx")) {
                    // Excel file
                    intent.setDataAndType(uri, "application/vnd.ms-excel");
                } else if (url.contains(".zip") || url.contains(".rar")) {
                    // WAV audio file
                    intent.setDataAndType(uri, "application/x-wav");
                } else if (url.contains(".rtf")) {
                    // RTF file
                    intent.setDataAndType(uri, "application/rtf");
                } else if (url.contains(".wav") || url.contains(".mp3")) {
                    // WAV audio file
                    intent.setDataAndType(uri, "audio/x-wav");
                } else if (url.contains(".gif")) {
                    // GIF file
                    intent.setDataAndType(uri, "image/gif");
                } else if (url.contains(".jpg") || url.contains(".jpeg") || url.contains(".png")) {
                    // JPG file
                    intent.setDataAndType(uri, "image/jpeg");
                } else if (url.contains(".txt")) {
                    // Text file
                    intent.setDataAndType(uri, "text/plain");
                } else if (url.contains(".3gp") || url.contains(".mpg") || url.contains(".mpeg") || url.contains(".mpe") || url.contains(".mp4") || url.contains(".avi")) {
                    // Video files
                    intent.setDataAndType(uri, "video/*");
                } else {
                    //if you want you can also define the intent type for any other file
                    //additionally use else clause below, to manage other unknown extensions
                    //in this case, Android will show all applications installed on the device
                    //so you can choose which application to use
                    intent.setDataAndType(uri, "/");
                }

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return gridView;
    }
}