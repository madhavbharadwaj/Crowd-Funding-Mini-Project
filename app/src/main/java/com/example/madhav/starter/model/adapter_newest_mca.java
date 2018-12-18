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

public class adapter_newest_mca extends RecyclerView.Adapter<adapter_newest_mca.ViewHolder> {

    private List<newestItemMca> ui;
    // private CardView cardView;
    private Context context;

    public adapter_newest_mca(List<newestItemMca> ui, Context context) {
        this.ui = ui;
        this.context = context;
    }

    @NonNull
    @Override
    public adapter_newest_mca.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.newest_mca_items,viewGroup,false);

        return new adapter_newest_mca.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_newest_mca.ViewHolder viewHolder, int i) {

        newestItemMca newestItemMca = ui.get(i);



        // viewHolder.itemView.setBackgroundColor(Color.parseColor("#008080"));
        viewHolder.head_newest_mca.setText("Title : " + newestItemMca.getHead_new_mca());
        viewHolder.desc_newest_mca.setText("Description : "+ newestItemMca.getDesc_new_mca());

        viewHolder.email_newest_mca.setVisibility(View.GONE);
        viewHolder.domain_newest_mca.setVisibility(View.GONE);
        viewHolder.category_newest_mca.setVisibility(View.GONE);
        viewHolder.tou_newest_mca.setVisibility(View.GONE);

        viewHolder.pgitlink_newest_mca.setVisibility(View.GONE);





    }

    @Override
    public int getItemCount() {
        return ui.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView head_newest_mca;
        public TextView desc_newest_mca;

        public TextView email_newest_mca;
        public TextView domain_newest_mca;
        public TextView category_newest_mca;
        public TextView tou_newest_mca;
        public TextView pgitlink_newest_mca;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            head_newest_mca = (TextView) itemView.findViewById(R.id.head_newest_mca);
            desc_newest_mca = (TextView) itemView.findViewById(R.id.des_newest_mca);
            email_newest_mca = (TextView) itemView.findViewById(R.id.email_newest_mca);
            domain_newest_mca =(TextView) itemView.findViewById(R.id.domain_newest_mca);
            category_newest_mca   =(TextView) itemView.findViewById(R.id.category_newest_mca);
            tou_newest_mca = (TextView) itemView.findViewById(R.id.tou_newest_mca);
            pgitlink_newest_mca =(TextView) itemView.findViewById(R.id.pgitlink_newest_mca);



        }

        @Override
        public void onClick(View view) {
            int pos =getAdapterPosition();
            newestItemMca newestItemMca = ui.get(pos);



            Intent it = new Intent(context,desc.class);
/*
            it.putExtra("header",newestItem.getHead_new());
            it.putExtra("description",newestItem.getDesc_new());
            it.putExtra("email",newestItem.getEmail_new());
            it.putExtra("domain",newestItem.getDomain_new());
            it.putExtra("category",newestItem.getCategory_new());
            it.putExtra("tou",newestItem.getTou_new());
            it.putExtra("pgl",newestItem.getPro_git_link());
            context.startActivity(it);*/

            if (SaveSharedPreference.getLoggedStatus(context.getApplicationContext())) {


                it.putExtra("header",newestItemMca.getHead_new_mca());
                it.putExtra("description",newestItemMca.getDesc_new_mca());
                it.putExtra("email",newestItemMca.getEmail_new_mca());
                it.putExtra("domain",newestItemMca.getDomain_new_mca());
                it.putExtra("category",newestItemMca.getCategory_new_mca());
                it.putExtra("tou",newestItemMca.getTou_new_mca());
                it.putExtra("pgl",newestItemMca.getPro_git_link_mca());
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
