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

public class adapter_newest_icl extends RecyclerView.Adapter<adapter_newest_icl.ViewHolder> {

    private List<newestItemIcl> ui;
    // private CardView cardView;
    private Context context;

    public adapter_newest_icl(List<newestItemIcl> ui, Context context) {
        this.ui = ui;
        this.context = context;
    }

    @NonNull
    @Override
    public adapter_newest_icl.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.newest_icl_items,viewGroup,false);

        return new adapter_newest_icl.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_newest_icl.ViewHolder viewHolder, int i) {

        newestItemIcl newestItemIcl = ui.get(i);



        // viewHolder.itemView.setBackgroundColor(Color.parseColor("#008080"));
        viewHolder.head_newest_icl.setText("Title : " + newestItemIcl.getHead_new_icl());
        viewHolder.desc_newest_icl.setText("Description : "+ newestItemIcl.getDesc_new_icl());

        viewHolder.email_newest_icl.setVisibility(View.GONE);
        viewHolder.domain_newest_icl.setVisibility(View.GONE);
        viewHolder.category_newest_icl.setVisibility(View.GONE);
        viewHolder.tou_newest_icl.setVisibility(View.GONE);

        viewHolder.pgitlink_newest_icl.setVisibility(View.GONE);





    }

    @Override
    public int getItemCount() {
        return ui.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView head_newest_icl;
        public TextView desc_newest_icl;

        public TextView email_newest_icl;
        public TextView domain_newest_icl;
        public TextView category_newest_icl;
        public TextView tou_newest_icl;
        public TextView pgitlink_newest_icl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            head_newest_icl = (TextView) itemView.findViewById(R.id.head_newest_icl);
            desc_newest_icl = (TextView) itemView.findViewById(R.id.des_newest_icl);
            email_newest_icl = (TextView) itemView.findViewById(R.id.email_newest_icl);
            domain_newest_icl =(TextView) itemView.findViewById(R.id.domain_newest_icl);
            category_newest_icl   =(TextView) itemView.findViewById(R.id.category_newest_icl);
            tou_newest_icl = (TextView) itemView.findViewById(R.id.tou_newest_icl);
            pgitlink_newest_icl =(TextView) itemView.findViewById(R.id.pgitlink_newest_icl);



        }

        @Override
        public void onClick(View view) {
            int pos =getAdapterPosition();
            newestItemIcl newestItemIcl = ui.get(pos);



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


                it.putExtra("header",newestItemIcl.getHead_new_icl());
                it.putExtra("description",newestItemIcl.getDesc_new_icl());
                it.putExtra("email",newestItemIcl.getEmail_new_icl());
                it.putExtra("domain",newestItemIcl.getDomain_new_icl());
                it.putExtra("category",newestItemIcl.getCategory_new_icl());
                it.putExtra("tou",newestItemIcl.getTou_new_icl());
                it.putExtra("pgl",newestItemIcl.getPro_git_link_icl());
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

