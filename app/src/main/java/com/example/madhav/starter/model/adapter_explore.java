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

import com.example.madhav.starter.Home.stats;
import com.example.madhav.starter.R;
import com.example.madhav.starter.login_signup.SaveSharedPreference;

import java.util.List;

public class adapter_explore extends RecyclerView.Adapter<adapter_explore.ViewHolder> {


    private List<exploreItem> ui;
    // private CardView cardView;
    private Context context;

    public adapter_explore(List<exploreItem> ui, Context context) {
        this.ui = ui;
        this.context = context;
    }

    @NonNull
    @Override
    public adapter_explore.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.explore_items,viewGroup,false);

        return new adapter_explore.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_explore.ViewHolder viewHolder, int i) {
        exploreItem exploreItem = ui.get(i);



        // viewHolder.itemView.setBackgroundColor(Color.parseColor("#008080"));
        viewHolder.head_exp.setText("Title : " + exploreItem.getHead_exp());
        viewHolder.desc_exp.setText("Description : "+ exploreItem.getDesc_exp());

        viewHolder.email_exp.setVisibility(View.GONE);
        viewHolder.domain_exp.setVisibility(View.GONE);
        viewHolder.category_exp.setVisibility(View.GONE);
        viewHolder.tou_exp.setVisibility(View.GONE);

        viewHolder.pgitlink_exp.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return ui.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView head_exp;
        public TextView desc_exp;

        public TextView email_exp;
        public TextView domain_exp;
        public TextView category_exp;
        public TextView tou_exp;
        public TextView pgitlink_exp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            head_exp = (TextView) itemView.findViewById(R.id.head_exp);
            desc_exp = (TextView) itemView.findViewById(R.id.des_exp);
            email_exp = (TextView) itemView.findViewById(R.id.email_exp);
            domain_exp =(TextView) itemView.findViewById(R.id.domain_exp);
            category_exp   =(TextView) itemView.findViewById(R.id.category_exp);
            tou_exp = (TextView) itemView.findViewById(R.id.tou_exp);
            pgitlink_exp =(TextView) itemView.findViewById(R.id.pgitlink_exp);



        }

        @Override
        public void onClick(View view) {
            int pos =getAdapterPosition();
            exploreItem exploreItem = ui.get(pos);



            Intent it = new Intent(context,desc.class);


            if (SaveSharedPreference.getLoggedStatus(context.getApplicationContext())) {


                it.putExtra("header",exploreItem.getHead_exp());
                it.putExtra("description",exploreItem.getDesc_exp());
                it.putExtra("email",exploreItem.getEmail_exp());
                it.putExtra("domain",exploreItem.getDomain_exp());
                it.putExtra("category",exploreItem.getCategory_exp());
                it.putExtra("tou",exploreItem.getTou_exp());
                it.putExtra("pgl",exploreItem.getPro_git_link_exp());
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
