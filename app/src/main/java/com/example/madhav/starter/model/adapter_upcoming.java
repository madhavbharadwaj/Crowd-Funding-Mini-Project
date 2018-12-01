package com.example.madhav.starter.model;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhav.starter.R;
import com.example.madhav.starter.login_signup.SaveSharedPreference;

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
        viewHolder.head_upc.setText("Title : " +upcomingItem.getHead_upcg());
        viewHolder.desc_upc.setText("Description : " +upcomingItem.getDesc_upcg());

        viewHolder.email_upc.setVisibility(View.GONE);
        viewHolder.domain_upc.setVisibility(View.GONE);
        viewHolder.category_upc.setVisibility(View.GONE);
        viewHolder.tou_upc.setVisibility(View.GONE);
        viewHolder.pgitlink_upc.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return ui.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView head_upc;
        public TextView desc_upc;

        public TextView email_upc;
        public TextView domain_upc;
        public TextView category_upc;
        public TextView tou_upc;
        public TextView pgitlink_upc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            head_upc = (TextView) itemView.findViewById(R.id.head_upc);
            desc_upc = (TextView) itemView.findViewById(R.id.des_upc);
            email_upc = (TextView) itemView.findViewById(R.id.email_upc);
            domain_upc = (TextView) itemView.findViewById(R.id.domain_upc);
            category_upc = (TextView) itemView.findViewById(R.id.category_upc);
            tou_upc = (TextView) itemView.findViewById(R.id.tou_upc);
            pgitlink_upc = (TextView) itemView.findViewById(R.id.pgitlink_upc);
        }

        @Override
        public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(), "Access Denied ! Only approved projects description can be viewed",
                        Toast.LENGTH_SHORT).show();



        }
    }
}
