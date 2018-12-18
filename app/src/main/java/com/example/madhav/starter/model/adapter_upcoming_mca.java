package com.example.madhav.starter.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhav.starter.R;

import java.util.List;

public class adapter_upcoming_mca extends RecyclerView.Adapter<adapter_upcoming_mca.ViewHolder> {

    private List<upcomingItemMca> ui;
    private Context context;

    public adapter_upcoming_mca(List<upcomingItemMca> ui, Context context) {
        this.ui = ui;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.upcoming_mca_items,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        upcomingItemMca upcomingItemMca = ui.get(i);
        viewHolder.head_upc_mca.setText("Title : " +upcomingItemMca.getHead_upcg_mca());
        viewHolder.desc_upc_mca.setText("Description : " +upcomingItemMca.getDesc_upcg_mca());

        viewHolder.email_upc_mca.setVisibility(View.GONE);
        viewHolder.domain_upc_mca.setVisibility(View.GONE);
        viewHolder.category_upc_mca.setVisibility(View.GONE);
        viewHolder.tou_upc_mca.setVisibility(View.GONE);
        viewHolder.pgitlink_upc_mca.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return ui.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView head_upc_mca;
        public TextView desc_upc_mca;

        public TextView email_upc_mca;
        public TextView domain_upc_mca;
        public TextView category_upc_mca;
        public TextView tou_upc_mca;
        public TextView pgitlink_upc_mca;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            head_upc_mca = (TextView) itemView.findViewById(R.id.head_upc_mca);
            desc_upc_mca = (TextView) itemView.findViewById(R.id.des_upc_mca);
            email_upc_mca = (TextView) itemView.findViewById(R.id.email_upc_mca);
            domain_upc_mca = (TextView) itemView.findViewById(R.id.domain_upc_mca);
            category_upc_mca = (TextView) itemView.findViewById(R.id.category_upc_mca);
            tou_upc_mca = (TextView) itemView.findViewById(R.id.tou_upc_mca);
            pgitlink_upc_mca = (TextView) itemView.findViewById(R.id.pgitlink_upc_mca);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context.getApplicationContext(), "Access Denied ! Only approved projects description can be viewed",
                    Toast.LENGTH_SHORT).show();



        }
    }
}

