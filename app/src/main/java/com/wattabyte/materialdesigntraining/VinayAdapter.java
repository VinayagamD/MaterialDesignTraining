package com.wattabyte.materialdesigntraining;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wattabyte.materialdesigntraining.util.Information;

import java.util.Collections;
import java.util.List;

/**
 * Created by Vinayagam on 9/9/15.
 */
public class VinayAdapter extends RecyclerView.Adapter<VinayAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    Context context;

/*Method 2*/
//    private ClickListener clickListener;

    List<Information> data = Collections.emptyList();

    public VinayAdapter(Context context,List<Information> data) {
       inflater =  LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("Vinay", "onCreateHolder called ");
       View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,  int position) {
        Log.d("Vinay", "onBindHolder called " + position);
        Information current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.itemId);

       /* holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Item clicked at "+position,Toast.LENGTH_SHORT).show();

            }
        });*/
    }

    /*Method 2*/
   /*
   public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }
    */

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        ImageView icon;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
//            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            delete(getAdapterPosition());
            /*Method 1*/
//            context.startActivity(new Intent(context,SubActivityCustom.class));

             /*Method 2*/
//            if (clickListener != null){
//                clickListener.itemClicked(v,getAdapterPosition());
//            }

        }
    }

    /*Method 2*/
//    public interface ClickListener{
//        void itemClicked(View view, int position);
//    }
}
