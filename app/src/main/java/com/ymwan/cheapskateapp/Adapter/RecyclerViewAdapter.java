package com.ymwan.cheapskateapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ymwan.cheapskateapp.CustomFilter;
import com.ymwan.cheapskateapp.R;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable{

    Context context;

    public List<DataAdapter> dataAdapters, filterList;

    CustomFilter filter;

    public OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;
    }

    public RecyclerViewAdapter(List<DataAdapter> getDataAdapter, Context context){

        super();

        this.dataAdapters = getDataAdapter;
        this.context = context;
        this.filterList = dataAdapters;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        DataAdapter dataAdapter =  dataAdapters.get(position);

        viewHolder.productName.setText(dataAdapter.getProductName());

       // viewHolder.productPrice.setText(dataAdapter.getProductPrice());

       // viewHolder.productDate.setText(dataAdapter.getProductDate());

        viewHolder.productStore.setText(dataAdapter.getProductStore());

    }

    @Override
    public int getItemCount() {

        return dataAdapters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView productName;
        //public TextView productPrice;
       // public TextView productDate;
        public TextView productStore;

        public ViewHolder(View itemView) {

            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.textView2) ;
           // productPrice = (TextView) itemView.findViewById(R.id.textView4) ;
            //productDate = (TextView) itemView.findViewById(R.id.textView6) ;
            productStore = (TextView) itemView.findViewById(R.id.textView8) ;

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    //RETURN FILTER OBJ
    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new CustomFilter(filterList,this);
        }

        return filter;
    }
}
