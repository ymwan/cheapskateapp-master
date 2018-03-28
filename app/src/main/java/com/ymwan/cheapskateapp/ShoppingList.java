package com.ymwan.cheapskateapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by wanyikmun on 28/3/2018.
 */

public class ShoppingList extends AppCompatActivity {

    Button searchStoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shopping_list);

        searchStoreButton = (Button)findViewById(R.id.btnSearchStore);
        searchStoreButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Uri gmmIntentUri = Uri.parse("geo:0,0?q=supermarket");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }
}
