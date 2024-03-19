package com.app.fermierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustomViewStock extends BaseAdapter {
    Context context;
    String[] cfpname, stock, stockid;

    public CustomViewStock(Context applicationContext, String[] cfpname, String[] stock, String[] stockid) {
        this.context = applicationContext;
        this.cfpname = cfpname;
        this.stock = stock;
        this.stockid = stockid;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_stock,null);

        }
        else
        {
            gridView=(View)convertView;

        }
        TextView tname = gridView.findViewById(R.id.textView16);
        TextView tstock = gridView.findViewById(R.id.textView17);
        ImageButton imgv = gridView.findViewById(R.id.imageButton);
        EditText edt = gridView.findViewById(R.id.editTextNumber);


        tname.setText(cfpname[position]);
        tstock.setText("Current Stock ( "+stock[position]+" )");

        imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String count = edt.getText().toString();
                if (count.isEmpty()){
                    edt.setError("Enter quantity");
                    return;
                }
                if (Integer.parseInt(count)>Integer.parseInt(stock[position])){
                    edt.setError("Quantity is out of Available");
                }
                else {
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    String url = sh.getString("url", "")+"/add_cart";
                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                    try {
                                        JSONObject jsonObj = new JSONObject(response);
                                        if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                            Toast.makeText(context, "Successfully Added to Cart", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(context, "Not found", Toast.LENGTH_LONG).show();
                                        }

                                    } catch (Exception e) {
                                        Toast.makeText(context, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                    ) {

                        //                value Passing android to python
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();

                            params.put("farmerid", sh.getString("lid", ""));//passing to python
                            params.put("stockid", stockid[position]);
                            params.put("count", count);


                            return params;
                        }
                    };


                    int MY_SOCKET_TIMEOUT_MS = 100000;

                    postRequest.setRetryPolicy(new DefaultRetryPolicy(
                            MY_SOCKET_TIMEOUT_MS,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    requestQueue.add(postRequest);


                }
            }
        });
        return gridView;
    }
}