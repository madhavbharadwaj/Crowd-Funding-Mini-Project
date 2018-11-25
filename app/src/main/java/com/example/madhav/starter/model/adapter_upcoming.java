package com.example.madhav.starter.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.madhav.starter.R;

import java.util.List;

public class adapter_upcoming extends RecyclerView.Adapter<adapter_upcoming.ViewHolder> {

    private List<upcomingItem> ui;
    private Context context;

    public adapter_upcoming(List<upcomingItem> ui, Context context) {
        this.ui = ui;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.upcoming_items,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        upcomingItem upcomingItem = ui.get(i);
        viewHolder.head.setText(upcomingItem.getHead());
        viewHolder.desc.setText(upcomingItem.getDesc());
    }

    @Override
    public int getItemCount() {
        return ui.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView head;
        public TextView desc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            head = (TextView) itemView.findViewById(R.id.head);
            desc = (TextView) itemView.findViewById(R.id.des);
        }
    }
}
