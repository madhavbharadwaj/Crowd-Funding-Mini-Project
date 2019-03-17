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

public class adapter_explore_icl extends RecyclerView.Adapter<adapter_explore_icl.ViewHolder> {


    private List<exploreItemIcl> ui;
    // private CardView cardView;
    private Context context;

    public adapter_explore_icl(List<exploreItemIcl> ui, Context context) {
        this.ui = ui;
        this.context = context;
    }

    @NonNull
    @Override
    public adapter_explore_icl.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.explore_icl_items,viewGroup,false);

        return new adapter_explore_icl.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_explore_icl.ViewHolder viewHolder, int i) {
        exploreItemIcl exploreItemIcl = ui.get(i);



        // viewHolder.itemView.setBackgroundColor(Color.parseColor("#008080"));
        viewHolder.head_exp_icl.setText(exploreItemIcl.getHead_exp_icl());
        viewHolder.desc_exp_icl.setText(exploreItemIcl.getDesc_exp_icl());

        viewHolder.email_exp_icl.setVisibility(View.GONE);
        viewHolder.domain_exp_icl.setVisibility(View.GONE);
        viewHolder.category_exp_icl.setVisibility(View.GONE);
        viewHolder.tou_exp_icl.setVisibility(View.GONE);

        viewHolder.pgitlink_exp_icl.setVisibility(View.GONE);
    }



    @Override
    public int getItemCount() {
        return ui.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView head_exp_icl;
        public TextView desc_exp_icl;

        public TextView email_exp_icl;
        public TextView domain_exp_icl;
        public TextView category_exp_icl;
        public TextView tou_exp_icl;
        public TextView pgitlink_exp_icl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            head_exp_icl = (TextView) itemView.findViewById(R.id.head_exp_icl);
            desc_exp_icl = (TextView) itemView.findViewById(R.id.des_exp_icl);
            email_exp_icl = (TextView) itemView.findViewById(R.id.email_exp_icl);
            domain_exp_icl =(TextView) itemView.findViewById(R.id.domain_exp_icl);
            category_exp_icl   =(TextView) itemView.findViewById(R.id.category_exp_icl);
            tou_exp_icl = (TextView) itemView.findViewById(R.id.tou_exp_icl);
            pgitlink_exp_icl =(TextView) itemView.findViewById(R.id.pgitlink_exp_icl);



        }

        @Override
        public void onClick(View view) {
            int pos =getAdapterPosition();
            exploreItemIcl exploreItemIcl = ui.get(pos);



            Intent it = new Intent(context,desc.class);


            if (SaveSharedPreference.getLoggedStatus(context.getApplicationContext())) {


                it.putExtra("header",exploreItemIcl.getHead_exp_icl());
                it.putExtra("description",exploreItemIcl.getDesc_exp_icl());
                it.putExtra("email",exploreItemIcl.getEmail_exp_icl());
                it.putExtra("domain",exploreItemIcl.getDomain_exp_icl());
                it.putExtra("category",exploreItemIcl.getCategory_exp_icl());
                it.putExtra("tou",exploreItemIcl.getTou_exp_icl());
                it.putExtra("pgl",exploreItemIcl.getPro_git_link_exp_icl());
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

