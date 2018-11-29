package com.example.madhav.starter.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.madhav.starter.R;

import java.util.List;

public class adapter_newest extends RecyclerView.Adapter<adapter_newest.ViewHolder> {

    private List<newestItem> ui;
   // private CardView cardView;
    private Context context;

    public adapter_newest(List<newestItem> ui, Context context) {
        this.ui = ui;
        this.context = context;
    }

    @NonNull
    @Override
    public adapter_newest.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.newest_items,viewGroup,false);

        return new adapter_newest.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_newest.ViewHolder viewHolder, int i) {

        newestItem newestItem = ui.get(i);



        viewHolder.head_newest.setText("Title : " + newestItem.getHead_new());
        viewHolder.desc_newest.setText("Description : "+ newestItem.getDesc_new());



    }

    @Override
    public int getItemCount() {
        return ui.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView head_newest;
        public TextView desc_newest;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            head_newest = (TextView) itemView.findViewById(R.id.head_newest);
            desc_newest = (TextView) itemView.findViewById(R.id.des_newest);
        }
    }
}
