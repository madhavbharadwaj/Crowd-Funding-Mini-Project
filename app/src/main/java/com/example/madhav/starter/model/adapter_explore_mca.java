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

public class adapter_explore_mca extends RecyclerView.Adapter<adapter_explore_mca.ViewHolder> {


    private List<exploreItemMca> ui;
    // private CardView cardView;
    private Context context;

    public adapter_explore_mca(List<exploreItemMca> ui, Context context) {
        this.ui = ui;
        this.context = context;
    }

    @NonNull
    @Override
    public adapter_explore_mca.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.explore_mca_items,viewGroup,false);

        return new adapter_explore_mca.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_explore_mca.ViewHolder viewHolder, int i) {
        exploreItemMca exploreItemMca = ui.get(i);



        // viewHolder.itemView.setBackgroundColor(Color.parseColor("#008080"));
        viewHolder.head_exp_mca.setText("Title : " + exploreItemMca.getHead_exp_mca());
        viewHolder.desc_exp_mca.setText("Description : "+ exploreItemMca.getDesc_exp_mca());

        viewHolder.email_exp_mca.setVisibility(View.GONE);
        viewHolder.domain_exp_mca.setVisibility(View.GONE);
        viewHolder.category_exp_mca.setVisibility(View.GONE);
        viewHolder.tou_exp_mca.setVisibility(View.GONE);

        viewHolder.pgitlink_exp_mca.setVisibility(View.GONE);
    }



    @Override
    public int getItemCount() {
        return ui.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView head_exp_mca;
        public TextView desc_exp_mca;

        public TextView email_exp_mca;
        public TextView domain_exp_mca;
        public TextView category_exp_mca;
        public TextView tou_exp_mca;
        public TextView pgitlink_exp_mca;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            head_exp_mca = (TextView) itemView.findViewById(R.id.head_exp_mca);
            desc_exp_mca = (TextView) itemView.findViewById(R.id.des_exp_mca);
            email_exp_mca = (TextView) itemView.findViewById(R.id.email_exp_mca);
            domain_exp_mca =(TextView) itemView.findViewById(R.id.domain_exp_mca);
            category_exp_mca   =(TextView) itemView.findViewById(R.id.category_exp_mca);
            tou_exp_mca = (TextView) itemView.findViewById(R.id.tou_exp_mca);
            pgitlink_exp_mca =(TextView) itemView.findViewById(R.id.pgitlink_exp_mca);



        }

        @Override
        public void onClick(View view) {
            int pos =getAdapterPosition();
            exploreItemMca exploreItemMca = ui.get(pos);



            Intent it = new Intent(context,desc.class);


            if (SaveSharedPreference.getLoggedStatus(context.getApplicationContext())) {


                it.putExtra("header",exploreItemMca.getHead_exp_mca());
                it.putExtra("description",exploreItemMca.getDesc_exp_mca());
                it.putExtra("email",exploreItemMca.getEmail_exp_mca());
                it.putExtra("domain",exploreItemMca.getDomain_exp_mca());
                it.putExtra("category",exploreItemMca.getCategory_exp_mca());
                it.putExtra("tou",exploreItemMca.getTou_exp_mca());
                it.putExtra("pgl",exploreItemMca.getPro_git_link_exp_mca());
                context.startActivity(it);

            } else {
                Toast.makeText(context.getApplicationContext(), "Please login to view project description",
                        Toast.LENGTH_SHORT).show();
            }



            // Log.d("pos ", String.valueOf(newestItem));
            //  head_newest = (TextView) itemView.findViewById(R.id.head_newest);

        }
    }
}

