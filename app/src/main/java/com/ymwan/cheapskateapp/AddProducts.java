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

public class AddProducts extends AppCompatActivity {

    private EditText productName;
    private EditText productPrice;
    private EditText productDate;
    private EditText productStore;
    private RequestQueue requestQueue;
    private StringRequest request;
    private Button btnSubmitData;
    private static final String ADD_PRODUCTS_URL = AppConfig.url+"addProduct.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_products);

        productName = (EditText) findViewById(R.id.inputName);
        productPrice = (EditText) findViewById(R.id.inputPrice);
        productDate = (EditText) findViewById(R.id.inputDate);
        productStore = (EditText) findViewById(R.id.inputStore);

        requestQueue = Volley.newRequestQueue(this);

        btnSubmitData = (Button)findViewById(R.id.btnSubmitData);

        btnSubmitData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                request = new StringRequest(Request.Method.POST, ADD_PRODUCTS_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("success")){
                                Toast.makeText(getApplicationContext(), "Success" +jsonObject.getString("success"),Toast.LENGTH_SHORT).show();
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
                })
                {
                    @Override
                    protected Map<String,String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<String, String>();
                        hashMap.put("product_name", productName.getText().toString());
                        hashMap.put("product_price", productPrice.getText().toString());
                        hashMap.put("product_date", productDate.getText().toString());
                        hashMap.put("product_store", productStore.getText().toString());
                        return hashMap;
                    }
                };

                requestQueue.add(request);

                productName.getText().clear();
                productPrice.getText().clear();
                productDate.getText().clear();
                productStore.getText().clear();

                Toast.makeText(getApplicationContext(), "Data submitted successfully!",
                        Toast.LENGTH_LONG).show();

            }
        });
    }

}