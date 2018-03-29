package com.ymwan.cheapskateapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ymwan.cheapskateapp.Adapter.DataAdapter;

import java.util.List;

/**
 * Created by wanyikmun on 28/3/2018.
 */

public class ShoppingList extends AppCompatActivity {

    private List<DataAdapter> mCartList;
    private DataAdapter mProductAdapter;

    Button searchStoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shopping_list);


/*        mCartList = ShoppingListHelper.getCartList();

        // Make sure to clear the selections
        for(int i=0; i<mCartList.size(); i++) {
            mCartList.get(i).selected = false;
        }


        // Create the list
        final ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
        mProductAdapter = new DataAdapter(mCartList, getLayoutInflater(), true);
        listViewCatalog.setAdapter(mProductAdapter);

        listViewCatalog.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent productDetailsIntent = new Intent(getBaseContext(),ProductDetails.class);
                productDetailsIntent.putExtra(ShoppingListHelper.PRODUCT_INDEX, position);
                startActivity(productDetailsIntent);
            }
        });

*/
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

/*
    @Override
    protected void onResume() {
        super.onResume();

        // Refresh the data
        if(mProductAdapter != null) {
            mProductAdapter.notifyDataSetChanged();
        }

        double subTotal = 0;
        for(Product p : mCartList) {
            int quantity = ShoppingListHelper.getProductQuantity(p);
            subTotal += p.price * quantity;
        }

        TextView productPriceTextView = (TextView) findViewById(R.id.TextViewSubtotal);
        productPriceTextView.setText("Subtotal: $" + subTotal);
    }
  */
    }
