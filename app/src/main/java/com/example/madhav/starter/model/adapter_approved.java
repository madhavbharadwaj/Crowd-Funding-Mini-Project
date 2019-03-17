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

public class adapter_approved  extends RecyclerView.Adapter<adapter_approved.ViewHolder>{


    private List<approvedItem> ui;
    // private CardView cardView;
    private Context context;

    public adapter_approved(List<approvedItem> ui, Context context) {
        this.ui = ui;
        this.context = context;
    }

    @NonNull
    @Override
    public adapter_approved.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.approved_items,viewGroup,false);

        return new adapter_approved.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_approved.ViewHolder viewHolder, int i) {
        approvedItem approvedItem = ui.get(i);



        // viewHolder.itemView.setBackgroundColor(Color.parseColor("#008080"));
        viewHolder.head_approved.setText(approvedItem.getHead_approved());
        viewHolder.desc_approved.setText(approvedItem.getDesc_approved());

        viewHolder.email_approved.setVisibility(View.GONE);
        viewHolder.domain_approved.setVisibility(View.GONE);
        viewHolder.category_approved.setVisibility(View.GONE);
        viewHolder.tou_approved.setVisibility(View.GONE);

        viewHolder.pgitlink_approved.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return ui.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView head_approved;
        public TextView desc_approved;

        public TextView email_approved;
        public TextView domain_approved;
        public TextView category_approved;
        public TextView tou_approved;
        public TextView pgitlink_approved;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            head_approved = (TextView) itemView.findViewById(R.id.head_approved);
            desc_approved = (TextView) itemView.findViewById(R.id.des_approved);
            email_approved = (TextView) itemView.findViewById(R.id.email_approved);
            domain_approved =(TextView) itemView.findViewById(R.id.domain_approved);
            category_approved   =(TextView) itemView.findViewById(R.id.category_approved);
            tou_approved = (TextView) itemView.findViewById(R.id.tou_approved);
            pgitlink_approved =(TextView) itemView.findViewById(R.id.pgitlink_approved);



        }

        @Override
        public void onClick(View view) {
            int pos =getAdapterPosition();
            approvedItem approvedItem = ui.get(pos);



            Intent it = new Intent(context,desc.class);


            if (SaveSharedPreference.getLoggedStatus(context.getApplicationContext())) {


                it.putExtra("header",approvedItem.getHead_approved());
                it.putExtra("description",approvedItem.getDesc_approved());
                it.putExtra("email",approvedItem.getEmail_approved());
                it.putExtra("domain",approvedItem.getDomain_approved());
                it.putExtra("category",approvedItem.getCategory_approved());
                it.putExtra("tou",approvedItem.getTou_approved());
                it.putExtra("pgl",approvedItem.getPro_git_link_approved());
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
