package com.app.fermierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ViewCpfActivity extends AppCompatActivity {
    SharedPreferences sh;
    Spinner sp;
    String[]dis={"--Select--","Crop","Fertilization","Pesticide"};
    String[] cfpname, description, photo, type, price;
    ListView list;
    String qry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cpf);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        list = findViewById(R.id.listView);


        sp = findViewById(R.id.spinner);
        ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,dis);
        sp.setAdapter(ad);
        String url = sh.getString("url", "")+"/viewcfp";
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position ==1){
                    qry = "crop";
                }
                else if(position==2){
                    qry = "Fertilizer";
                }
                else if(position==3){
                    qry = "Pesticide";
                }
                else{
                    qry = "all";
                }
                ProgressDialog pd = new ProgressDialog(ViewCpfActivity.this);
                pd.setMessage("Grabbing Data");
                pd.show();
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    pd.dismiss();
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                        JSONArray js= jsonObj.getJSONArray("data");
                                        cfpname=new String[js.length()];
                                        description=new String[js.length()];
                                        photo=new String[js.length()];
                                        type=new String[js.length()];
                                        price=new String[js.length()];


                                        for(int i=0;i<js.length();i++)
                                        {
                                            JSONObject u=js.getJSONObject(i);
                                            cfpname[i]=u.getString("name");
                                            description[i]=u.getString("description");
                                            photo[i]=u.getString("photo");
                                            type[i]=u.getString("type");
                                            price[i]=u.getString("price");


                                        }

                                        // ArrayAdapter<String> adpt=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,name);
                                        list.setAdapter(new CustomCfpActivity(getApplicationContext(),cfpname, description, photo, type, price));
                                        // l1.setAdapter(new Custom(getApplicationContext(),gamecode,name,type,discription,image,status));
                                    }


                                    // }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                                    }

                                }    catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        Map<String, String> params = new HashMap<String, String>();

                        String id=sh.getString("uid","");
                        params.put("qry",qry);
                        return params;
                    }
                };

                int MY_SOCKET_TIMEOUT_MS=100000;

                postRequest.setRetryPolicy(new DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(postRequest);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ViewCpfActivity.this, "Get "+cfpname[position]+" From Your Nearest Agricultural Office", Toast.LENGTH_LONG).show();
            }
        });
    }
}