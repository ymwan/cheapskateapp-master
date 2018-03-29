package com.ymwan.cheapskateapp;

import com.ymwan.cheapskateapp.Adapter.DataAdapter;

/**
 * Created by wanyikmun on 25/3/2018.
 */

public class ShoppingListEntry {

    private DataAdapter mProduct;
    private int mQuantity;

    public ShoppingListEntry(DataAdapter product, int quantity) {
        mProduct = product;
        mQuantity = quantity;
    }

    public DataAdapter getProduct() {
        return mProduct;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }
}
