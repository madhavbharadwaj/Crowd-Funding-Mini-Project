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

public class adapter_pending extends RecyclerView.Adapter<adapter_pending.ViewHolder> {

    private List<pendingItem> ui;
    // private CardView cardView;
    private Context context;

    public adapter_pending(List<pendingItem> ui, Context context) {
        this.ui = ui;
        this.context = context;
    }

    @NonNull
    @Override
    public adapter_pending.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.pending_items,viewGroup,false);

        return new adapter_pending.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_pending.ViewHolder viewHolder, int i) {
        pendingItem pendingItem = ui.get(i);



        // viewHolder.itemView.setBackgroundColor(Color.parseColor("#008080"));
        viewHolder.head_pending.setText(pendingItem.getHead_pending());
        viewHolder.desc_pending.setText(pendingItem.getDesc_pending());

        viewHolder.email_pending.setVisibility(View.GONE);
        viewHolder.domain_pending.setVisibility(View.GONE);
        viewHolder.category_pending.setVisibility(View.GONE);
        viewHolder.tou_pending.setVisibility(View.GONE);

        viewHolder.pgitlink_pending.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return ui.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView head_pending;
        public TextView desc_pending;

        public TextView email_pending;
        public TextView domain_pending;
        public TextView category_pending;
        public TextView tou_pending;
        public TextView pgitlink_pending;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            head_pending = (TextView) itemView.findViewById(R.id.head_pending);
            desc_pending = (TextView) itemView.findViewById(R.id.des_pending);
            email_pending = (TextView) itemView.findViewById(R.id.email_pending);
            domain_pending =(TextView) itemView.findViewById(R.id.domain_pending);
            category_pending   =(TextView) itemView.findViewById(R.id.category_pending);
            tou_pending = (TextView) itemView.findViewById(R.id.tou_pending);
            pgitlink_pending =(TextView) itemView.findViewById(R.id.pgitlink_pending);



        }

        @Override
        public void onClick(View view) {
            int pos =getAdapterPosition();
            pendingItem pendingItem = ui.get(pos);



            Intent it = new Intent(context,desc.class);


            if (SaveSharedPreference.getLoggedStatus(context.getApplicationContext())) {


                it.putExtra("header",pendingItem.getHead_pending());
                it.putExtra("description",pendingItem.getDesc_pending());
                it.putExtra("email",pendingItem.getEmail_pending());
                it.putExtra("domain",pendingItem.getDomain_pending());
                it.putExtra("category",pendingItem.getCategory_pending());
                it.putExtra("tou",pendingItem.getTou_pending());
                it.putExtra("pgl",pendingItem.getPro_git_link_pending());
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
