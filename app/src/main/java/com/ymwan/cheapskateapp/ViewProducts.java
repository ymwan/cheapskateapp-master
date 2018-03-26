package com.ymwan.cheapskateapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ymwan.cheapskateapp.Adapter.DataAdapter;
import com.ymwan.cheapskateapp.Adapter.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ViewProducts extends AppCompatActivity {

    private ArrayList<DataAdapter> dataAdapterArrayList = new ArrayList<>();

    RecyclerView recyclerView;

    SearchView sv;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerViewAdapter recyclerViewadapter;

    ProgressBar progressBar;

    JsonArrayRequest jsonArrayRequest ;

    ArrayList<String> Products;

    RequestQueue requestQueue ;

    String HTTP_SERVER_URL = AppConfig.url+"ProductDetails.php";

    View ChildView ;

    int RecyclerViewClickedItemPOS ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_products);

        Products = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);

        sv = (SearchView) findViewById(R.id.mSearch);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        // JSON data web call function call from here.
        JSON_WEB_CALL();

        recyclerViewadapter = new RecyclerViewAdapter(dataAdapterArrayList, this);

        recyclerView.setAdapter(recyclerViewadapter);

        //RecyclerView Item click listener code starts from here.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(ViewProducts.this, new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                ChildView = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(ChildView != null && gestureDetector.onTouchEvent(motionEvent)) {

                    //Getting RecyclerView Clicked item value.
                    RecyclerViewClickedItemPOS = Recyclerview.getChildAdapterPosition(ChildView);

                    //Printing RecyclerView Clicked item clicked value using Toast Message.
                    Toast.makeText(ViewProducts.this, Products.get(RecyclerViewClickedItemPOS), Toast.LENGTH_LONG).show();

                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //FILTER AS YOU TYPE
                recyclerViewadapter.getFilter().filter(query);
                return false;
            }
        });

        Button viewShoppingList = (Button) findViewById(R.id.ButtonViewShoppingList);
        viewShoppingList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent viewShoppingListIntent = new Intent(getBaseContext(), ShoppingList.class);
                startActivity(viewShoppingListIntent);
            }
        });
    }

    public void JSON_WEB_CALL(){

        jsonArrayRequest = new JsonArrayRequest(HTTP_SERVER_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            DataAdapter GetDataAdapter2 = new DataAdapter();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                GetDataAdapter2.setProductName(json.getString("product_name"));

                //GetDataAdapter2.setProductPrice(json.getString("product_price"));

                //Adding subject name here to show on click event.
                Products.add(json.getString("product_name"));

                //GetDataAdapter2.setProductDate(json.getString("product_date"));

                GetDataAdapter2.setProductStore(json.getString("product_store"));

            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

            dataAdapterArrayList.add(GetDataAdapter2);
        }

        progressBar.setVisibility(View.GONE);

        // refresh the adapter
        recyclerViewadapter.notifyDataSetChanged();
    }
}