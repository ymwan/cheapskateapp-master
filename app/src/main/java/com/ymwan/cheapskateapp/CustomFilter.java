package com.ymwan.cheapskateapp;

/**
 * Created by wanyikmun on 19/3/2018.
 */

import android.widget.Filter;

import com.ymwan.cheapskateapp.Adapter.DataAdapter;
import com.ymwan.cheapskateapp.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomFilter extends Filter{

    RecyclerViewAdapter recyclerViewadapter;
    List<DataAdapter> filterList;


    public CustomFilter(List<DataAdapter> filterList,RecyclerViewAdapter recyclerViewadapter)
    {
        this.recyclerViewadapter=recyclerViewadapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            List<DataAdapter> filteredProducts=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getProductName().toUpperCase().contains(constraint) || filterList.get(i).getProductStore().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PRODUCTS
                    filteredProducts.add(filterList.get(i));
                }
            }

            results.count=filteredProducts.size();
            results.values=filteredProducts;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }


        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        recyclerViewadapter.dataAdapters= (List<DataAdapter>) results.values;

        //REFRESH
        recyclerViewadapter.notifyDataSetChanged();
    }
}

