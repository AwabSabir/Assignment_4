package com.example.assighment_4;

import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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


public class MainActivity extends AppCompatActivity {
    private EditText email,password;
    private Button sing_in;
    private  static  String url="https://computercode123.000webhostapp.com/LoginDocter.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

          email=(EditText)findViewById(R.id.email);
          password=(EditText)findViewById(R.id.password);
        sing_in=(Button)findViewById(R.id.singin_btn) ;

        sing_in.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              insertDate();

            }
        });

    }

    public void open_new_Accont(View view) {
        Intent intent = new Intent(this,Sing_Up.class);
        startActivity(intent);
    }


    public void goback(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private  void insertDate(){
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                if(response.equalsIgnoreCase("sucessfully login")){
                    Intent intent=new Intent(getApplicationContext(),DocterList.class);
                    intent.putExtra("Email", email.getText().toString());
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email.getText().toString());
                params.put("password",password.getText().toString());
                return  params;
            }
        };
        RequestQueue i= Volley.newRequestQueue(this);
        i.add(request);
    }


}