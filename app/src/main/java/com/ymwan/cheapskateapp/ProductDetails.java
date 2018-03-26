package com.ymwan.cheapskateapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ymwan.cheapskateapp.Adapter.DataAdapter;

import java.util.List;

/**
 * Created by wanyikmun on 25/3/2018.
 */

public class ProductDetails extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.product_details);

        //List<DataAdapter> catalog = ShoppingListHelper.getCatalog(getResources());

        int productIndex = getIntent().getExtras().getInt(
                ShoppingListHelper.PRODUCT_INDEX);
        final DataAdapter selectedProduct = catalog.get(productIndex);

        TextView productTitleTextView = (TextView) findViewById(R.id.TextViewProductTitle);
        productTitleTextView.setText(selectedProduct.title);
        TextView productDateTextView = (TextView) findViewById(R.id.TextViewProductDate);
        productDateTextView.setText(selectedProduct.description);

        TextView productPriceTextView = (TextView) findViewById(R.id.TextViewProductPrice);
        productPriceTextView.setText("$" + selectedProduct.price);

        // Update the current quantity in the cart
       // TextView textViewCurrentQuantity = (TextView) findViewById(R.id.textViewCurrentlyInCart);
      //  textViewCurrentQuantity.setText("Currently in Cart: "
          //      + ShoppingCartHelper.getProductQuantity(selectedProduct));

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
                ShoppingListHelper.setQuantity(selectedProduct, quantity);

                // Close the activity
                finish();
            }
        });

    }

}
}
