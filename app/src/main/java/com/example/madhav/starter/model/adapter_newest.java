package com.example.madhav.starter.model;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;

import com.example.madhav.starter.Home.Dashboard;
import com.example.madhav.starter.R;
import com.example.madhav.starter.login_signup.RegLogActivity;
import com.example.madhav.starter.login_signup.SaveSharedPreference;
import com.example.madhav.starter.login_signup.profile;

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



       // viewHolder.itemView.setBackgroundColor(Color.parseColor("#008080"));
        viewHolder.head_newest.setText("Title : " + newestItem.getHead_new());
        viewHolder.desc_newest.setText("Description : "+ newestItem.getDesc_new());

        viewHolder.email_newest.setVisibility(View.GONE);
        viewHolder.domain_newest.setVisibility(View.GONE);
        viewHolder.category_newest.setVisibility(View.GONE);
        viewHolder.tou_newest.setVisibility(View.GONE);

        viewHolder.pgitlink_newest.setVisibility(View.GONE);





    }

    @Override
    public int getItemCount() {
        return ui.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView head_newest;
        public TextView desc_newest;

        public TextView email_newest;
        public TextView domain_newest;
        public TextView category_newest;
        public TextView tou_newest;
        public TextView pgitlink_newest;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            head_newest = (TextView) itemView.findViewById(R.id.head_newest);
            desc_newest = (TextView) itemView.findViewById(R.id.des_newest);
            email_newest = (TextView) itemView.findViewById(R.id.email_newest);
            domain_newest =(TextView) itemView.findViewById(R.id.domain_newest);
            category_newest   =(TextView) itemView.findViewById(R.id.category_newest);
            tou_newest = (TextView) itemView.findViewById(R.id.tou_newest);
            pgitlink_newest =(TextView) itemView.findViewById(R.id.pgitlink_newest);



        }

        @Override
        public void onClick(View view) {
            int pos =getAdapterPosition();
            newestItem newestItem = ui.get(pos);



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


                it.putExtra("header",newestItem.getHead_new());
                it.putExtra("description",newestItem.getDesc_new());
                it.putExtra("email",newestItem.getEmail_new());
                it.putExtra("domain",newestItem.getDomain_new());
                it.putExtra("category",newestItem.getCategory_new());
                it.putExtra("tou",newestItem.getTou_new());
                it.putExtra("pgl",newestItem.getPro_git_link());
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
