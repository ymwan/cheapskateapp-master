package com.ymwan.cheapskateapp;

import android.content.res.Resources;

import com.google.android.gms.analytics.ecommerce.Product;
import com.ymwan.cheapskateapp.Adapter.DataAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by wanyikmun on 26/3/2018.
 */

public class ShoppingListHelper {

    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";

    private static List<DataAdapter> dataAdapters;
    private static Map<DataAdapter, ShoppingListEntry> cartMap = new HashMap<DataAdapter, ShoppingListEntry>();

    //public static List<Product> getCatalog(Resources res){
    //      if(catalog == null) {
//            catalog = new Vector<Product>();
//            catalog.add(new Product("Dead or Alive", res
//                    .getDrawable(R.drawable.background),
//                    "Dead or Alive by Tom Clancy with Grant Blackwood", 29.99));
//            catalog.add(new Product("Switch", res
//                    .getDrawable(R.drawable.milk),
//                    "Switch by Chip Heath and Dan Heath", 24.99));
//            catalog.add(new Product("Watchmen", res
//                    .getDrawable(R.drawable.chocomilk),
//                    "Watchmen by Alan Moore and Dave Gibbons", 14.99));
//        }

    //    return catalog;
    //  }

    public static void setQuantity(DataAdapter product, int quantity) {
        // Get the current cart entry
        ShoppingListEntry curEntry = cartMap.get(product);

        // If the quantity is zero or less, remove the products
        if(quantity <= 0) {
            if(curEntry != null)
                removeProduct(product);
            return;
        }

        // If a current cart entry doesn't exist, create one
        if(curEntry == null) {
            curEntry = new ShoppingListEntry(product, quantity);
            cartMap.put(product, curEntry);
            return;
        }

        // Update the quantity
        curEntry.setQuantity(quantity);
    }

    public static int getProductQuantity(DataAdapter product) {
        // Get the current cart entry
        ShoppingListEntry curEntry = cartMap.get(product);

        if(curEntry != null)
            return curEntry.getQuantity();

        return 0;
    }

    public static void removeProduct(DataAdapter product) {
        cartMap.remove(product);
    }

    public static List<DataAdapter> getCartList() {
        List<DataAdapter> cartList = new Vector<DataAdapter>(cartMap.keySet().size());
        for(DataAdapter p : cartMap.keySet()) {
            cartList.add(p);
        }

        return cartList;
    }


}
