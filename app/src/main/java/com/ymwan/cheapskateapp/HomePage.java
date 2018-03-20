package com.ymwan.cheapskateapp;

/**
 * Created by wanyi on 27/2/2018.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    private Button btnUploadReceipt, btnViewProducts, btnAddProducts, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnUploadReceipt = (Button) findViewById(R.id.btnUploadReceipt);
        btnViewProducts = (Button) findViewById(R.id.btnViewProducts);
        btnAddProducts = (Button) findViewById(R.id.btnAddProducts);

        btnUploadReceipt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomePage.this, DocumentScannerActivity.class);
                startActivity(intent);
            }
        });

        btnViewProducts.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomePage.this, ViewProducts.class);
                startActivity(intent);
            }
        });

        btnAddProducts.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomePage.this, AddProducts.class);
                startActivity(intent);
            }
        });

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

}