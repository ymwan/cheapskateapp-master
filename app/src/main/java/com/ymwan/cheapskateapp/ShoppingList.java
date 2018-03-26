package com.ymwan.cheapskateapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ymwan.cheapskateapp.Adapter.DataAdapter;
import com.ymwan.cheapskateapp.Adapter.RecyclerViewAdapter;

import java.util.List;

/**
 * Created by wanyikmun on 21/3/2018.
 */

public class ShoppingList extends AppCompatActivity {

    private List<DataAdapter> mCartList;
    private RecyclerViewAdapter mProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list);


        mCartList = ShoppingListHelper.getCartList();

        // Make sure to clear the selections
        for(int i=0; i<mCartList.size(); i++) {
            mCartList.get(i).selected = false;
        }


        // Create the list
        final RecyclerView recyclerViewCatalog = (RecyclerView) findViewById(R.id.recyclerView1);
        mProductAdapter = new RecyclerViewAdapter(mCartList, getLayoutInflater(), true);
        recyclerViewCatalog.setAdapter(mProductAdapter);

        recyclerViewCatalog.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent productDetailsIntent = new Intent(getBaseContext(),ProductDetails.class);
                productDetailsIntent.putExtra(ShoppingListHelper.PRODUCT_INDEX, position);
                startActivity(productDetailsIntent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Refresh the data
        if(mProductAdapter != null) {
            mProductAdapter.notifyDataSetChanged();
        }

        double subTotal = 0;
        for(DataAdapter p : mCartList) {
            int quantity = ShoppingListHelper.getProductQuantity(p);
            subTotal += p.price * quantity;
        }

        TextView productPriceTextView = (TextView) findViewById(R.id.TextViewSubtotal);
        productPriceTextView.setText("Subtotal: $" + subTotal);
    }

}
