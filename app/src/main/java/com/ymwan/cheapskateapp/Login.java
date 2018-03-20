package com.ymwan.cheapskateapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanyi on 27/2/2018.
 */

public class Login extends AppCompatActivity {
    private Button btnLogin;
    private Button btnLinkToRegister;
    private EditText email;
    private EditText password;
    private RequestQueue requestQueue;
    private StringRequest request;
    private static final String LOGIN_URL = "http://10.212.77.9/android_login_api/user_control.php";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        requestQueue = Volley.newRequestQueue(this);

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                request = new StringRequest(Request.Method.POST, LOGIN_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("success")){
                                Toast.makeText(getApplicationContext(), "Success" +jsonObject.getString("success"),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),HomePage.class));
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Error" +jsonObject.getString("error"),Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

            }){
                    @Override
                    protected Map<String,String> getParams() throws AuthFailureError{
                        HashMap<String,String> hashMap = new HashMap<String, String>();
                        hashMap.put("user_email", email.getText().toString());
                        hashMap.put("user_password", password.getText().toString());
                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });

        // Link to Register Screen
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        Register.class);
                startActivity(i);
                finish();
            }
        });
    }
}
