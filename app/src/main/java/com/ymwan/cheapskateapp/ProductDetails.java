package com.ymwan.cheapskateapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.ecommerce.Product;
import com.ymwan.cheapskateapp.Adapter.DataAdapter;

import java.util.List;

import static com.ymwan.cheapskateapp.ViewProducts.product_date;
import static com.ymwan.cheapskateapp.ViewProducts.product_name;
import static com.ymwan.cheapskateapp.ViewProducts.product_price;
import static com.ymwan.cheapskateapp.ViewProducts.product_store;

/**
 * Created by wanyikmun on 25/3/2018.
 */

public class ProductDetails extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.product_details);

        Intent intent = getIntent();
        String productName = intent.getStringExtra(product_name);
        double productPrice = intent.getDoubleExtra(product_price,0);
        String productDate = intent.getStringExtra(product_date);
        String productStore = intent.getStringExtra(product_store);

        TextView textViewName = (TextView) findViewById(R.id.TextViewProductName);
        TextView textViewPrice = (TextView) findViewById(R.id.TextViewProductPrice);
        TextView textViewDate = (TextView) findViewById(R.id.TextViewProductDate);
        TextView textViewStore = (TextView) findViewById(R.id.TextViewProductStore);

        textViewName.setText(productName);
        String stringProductPrice= Double.toString(productPrice);
        textViewPrice.setText(stringProductPrice);
        textViewDate.setText(productDate);
        textViewStore.setText(productStore);

        //List<DataAdapter> catalog = ShoppingListHelper.getCatalog(getResources());
       // List<Product> catalog = ShoppingListHelper.getCatalog(getResources());


       /* int productIndex = getIntent().getExtras().getInt(
                ShoppingListHelper.PRODUCT_INDEX);
        final DataAdapter selectedProduct = catalog.get(productIndex);

        TextView productNameTextView = (TextView) findViewById(R.id.TextViewProductName);
        productNameTextView.setText(selectedProduct.getProductName());
        TextView productDateTextView = (TextView) findViewById(R.id.TextViewProductDate);
        productDateTextView.setText(selectedProduct.getProductDate());

        TextView productPriceTextView = (TextView) findViewById(R.id.TextViewProductPrice);
        productPriceTextView.setText("$" + selectedProduct.getProductPrice());

        // Update the current quantity in the cart
       TextView textViewCurrentQuantity = (TextView) findViewById(R.id.textViewCurrentlyInCart);
        textViewCurrentQuantity.setText("Currently in Cart: "
              + ShoppingListHelper.getProductQuantity(selectedProduct));

      */

        // Save a reference to the quantity edit text
        final EditText editTextQuantity = (EditText) findViewById(R.id.editTextQuantity);

        Button addToCartButton = (Button) findViewById(R.id.ButtonAddToCart);
        addToCartButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Check to see that a valid quantity was entered
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(editTextQuantity.getText()
                            .toString());

                    if (quantity < 0) {
                        Toast.makeText(getBaseContext(),
                                "Please enter a quantity of 0 or higher",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                } catch (Exception e) {
                    Toast.makeText(getBaseContext(),
                            "Please enter a numeric quantity",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                // If we make it here, a valid quantity was entered
                //ShoppingListHelper.setQuantity(selectedProduct, quantity);

                // Close the activity
                finish();
            }
        });

    }

}

