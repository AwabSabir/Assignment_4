package com.example.assighment_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Sing_Up extends AppCompatActivity {

    private Button singup;
    private EditText fname,lname,email,password;

    private  static  String url="https://computercode123.000webhostapp.com/DatabaseConnection.php";
    private  String getResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing__up);
        getSupportActionBar().hide();

        singup=(Button)findViewById(R.id.singupnew) ;
        fname=(EditText)findViewById(R.id.Fname) ;
        lname=(EditText)findViewById(R.id.Lname);
        email=(EditText)findViewById(R.id.Email);
        password=(EditText)findViewById(R.id.password);

        singup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String fnames=fname.getText().toString();
                String lnames=lname.getText().toString();
                String emails=email.getText().toString();
                String passwords=password.getText().toString();

                insertDate();



            }
        });
    }

    private  void insertDate(){
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                if(response.equalsIgnoreCase("your Account is created sucessfully")){
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        ){
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email.getText().toString());
                params.put("fname", fname.getText().toString());
                params.put("lname", lname.getText().toString());
                params.put("password",password.getText().toString());
                return  params;
            }
        };
        RequestQueue i= Volley.newRequestQueue(this);
        i.add(request);
    }




}