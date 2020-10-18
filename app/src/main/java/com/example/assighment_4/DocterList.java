package com.example.assighment_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocterList extends AppCompatActivity {
    private  static  String url;
    private Button btnlogout;
    ArrayList<String> val;
    RecyclerView recyclerView;
    private EditText find;
    private  AdopterClass adopterClass;
    private  String email;
    private  static String url2="https://computercode123.000webhostapp.com/LoogOut.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email=getIntent().getStringExtra("Email");
        Log.d("email", email);
        setContentView(R.layout.activity_docter_list);
        btnlogout=(Button)findViewById(R.id.logoutbtn);
        recyclerView=(RecyclerView)findViewById(R.id.displayListDocter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        find=(EditText) findViewById(R.id.findValues);


        btnlogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                logOut();
            }
        });


        find.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adopterClass.getFilter().filter(find.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        insertData();





    }

    @Override
    protected void onStop() {
        super.onStop();
        logOut();
    }

    private  void insertData(){
        StringRequest request=new StringRequest(Request.Method.POST, "https://computercode123.000webhostapp.com/fetchUserData.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        val=new ArrayList<>();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("data");
                            for(int j=0; j<jsonArray.length(); j++){
                                JSONObject object=jsonArray.getJSONObject(j);
                                String fname=object.getString("fname");
                                String lname=object.getString("lsnmae");
                                String caddress=object.getString("clinik");
                                String phone=object.getString("phone");

                                String fullname=fname + "\nSpeciality : " + lname+"\nClanic Address : "+caddress+"\nPhone No : "+phone;

                                val.add(fullname);
                                adopterClass=new AdopterClass(val);
                                recyclerView.setAdapter(adopterClass);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();


            }
        }

        ){
            protected Map<String ,String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;


            }
        };
        RequestQueue i= Volley.newRequestQueue(getApplicationContext());
        i.add(request);

    }

    private  void logOut(){
        StringRequest request=new StringRequest(Request.Method.POST, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        if(response.equalsIgnoreCase("LogOut Sucessfully")){
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                        Log.d("REsponse", response);

                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();


            }
        }

        ){
            protected Map<String ,String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                return params;

            }
        };
        RequestQueue i= Volley.newRequestQueue(getApplicationContext());
        i.add(request);

    }


}