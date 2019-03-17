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

public class adapter_upcoming_icl extends RecyclerView.Adapter<adapter_upcoming_icl.ViewHolder> {

    private List<upcomingItemIcl> ui;
    private Context context;

    public adapter_upcoming_icl(List<upcomingItemIcl> ui, Context context) {
        this.ui = ui;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.upcoming_icl_items,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        upcomingItemIcl upcomingItemIcl = ui.get(i);
        viewHolder.head_upc_icl.setText(upcomingItemIcl.getHead_upcg_icl());
        viewHolder.desc_upc_icl.setText(upcomingItemIcl.getDesc_upcg_icl());

        viewHolder.email_upc_icl.setVisibility(View.GONE);
        viewHolder.domain_upc_icl.setVisibility(View.GONE);
        viewHolder.category_upc_icl.setVisibility(View.GONE);
        viewHolder.tou_upc_icl.setVisibility(View.GONE);
        viewHolder.pgitlink_upc_icl.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return ui.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView head_upc_icl;
        public TextView desc_upc_icl;

        public TextView email_upc_icl;
        public TextView domain_upc_icl;
        public TextView category_upc_icl;
        public TextView tou_upc_icl;
        public TextView pgitlink_upc_icl;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            head_upc_icl = (TextView) itemView.findViewById(R.id.head_upc_icl);
            desc_upc_icl = (TextView) itemView.findViewById(R.id.des_upc_icl);
            email_upc_icl = (TextView) itemView.findViewById(R.id.email_upc_icl);
            domain_upc_icl = (TextView) itemView.findViewById(R.id.domain_upc_icl);
            category_upc_icl = (TextView) itemView.findViewById(R.id.category_upc_icl);
            tou_upc_icl = (TextView) itemView.findViewById(R.id.tou_upc_icl);
            pgitlink_upc_icl = (TextView) itemView.findViewById(R.id.pgitlink_upc_icl);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context.getApplicationContext(), "Access Denied ! Only approved projects description can be viewed",
                    Toast.LENGTH_SHORT).show();



        }
    }
}
