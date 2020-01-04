package com.example.mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button b1,b2;
    EditText e1,e2;
    RequestQueue rq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        b1=findViewById(R.id.b1);
 b2=findViewById(R.id.b2);

        Cache cache = new DiskBasedCache(getCacheDir(),1024*1024);
        Network network = new BasicNetwork(new HurlStack());
        rq = new RequestQueue(cache,network);
        rq.start();


 b1.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {

        final String s1=e1.getText().toString();
         final String s2=e2.getText().toString();


         if(s1.isEmpty() || s2.isEmpty()){

             Toast.makeText(MainActivity.this, "Please fill up all information", Toast.LENGTH_SHORT).show();

         }else {

             StringRequest sr=new StringRequest(Request.Method.POST, Javac.url, new Response.Listener<String>() {
                 @Override
                 public void onResponse(String response) {
                     Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                     rq.stop();
                 }
             }
                     , new Response.ErrorListener() {
                 @Override
                 public void onErrorResponse(VolleyError error) {

                 }
             }

             )

             {
                 @Override
                 protected Map<String, String> getParams() throws AuthFailureError {
                 Map<String, String> map = new HashMap<>();

                 map.put("email", s1);
                 map.put("password", s2);
                 Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
                 return map;

             }


             };
                     rq.add(sr);



         }


     }
 });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

}
