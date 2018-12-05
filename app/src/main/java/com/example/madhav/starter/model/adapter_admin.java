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

public class adapter_admin  extends RecyclerView.Adapter<adapter_admin.ViewHolder>{

    private List<adminItem> ui;
    // private CardView cardView;
    private Context context;

    public adapter_admin(List<adminItem> ui, Context context) {
        this.ui = ui;
        this.context = context;
    }

    @NonNull
    @Override
    public adapter_admin.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.admin_items,viewGroup,false);

        return new adapter_admin.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_admin.ViewHolder viewHolder, int i) {
        adminItem adminItem = ui.get(i);



        // viewHolder.itemView.setBackgroundColor(Color.parseColor("#008080"));
        viewHolder.head_admin.setText("Title : " + adminItem.getHead_admin());
        viewHolder.desc_admin.setText("Description : "+ adminItem.getDesc_admin());

        viewHolder.id_admin.setVisibility(View.GONE);
        viewHolder.email_admin.setVisibility(View.GONE);
        viewHolder.domain_admin.setVisibility(View.GONE);
        viewHolder.category_admin.setVisibility(View.GONE);
        viewHolder.tou_admin.setVisibility(View.GONE);

        viewHolder.pgitlink_admin.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return ui.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView id_admin;
        public TextView head_admin;
        public TextView desc_admin;

        public TextView email_admin;
        public TextView domain_admin;
        public TextView category_admin;
        public TextView tou_admin;
        public TextView pgitlink_admin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            id_admin = (TextView) itemView.findViewById(R.id.id_admin);
            head_admin = (TextView) itemView.findViewById(R.id.head_admin);
            desc_admin = (TextView) itemView.findViewById(R.id.des_admin);
            email_admin = (TextView) itemView.findViewById(R.id.email_admin);
            domain_admin =(TextView) itemView.findViewById(R.id.domain_admin);
            category_admin   =(TextView) itemView.findViewById(R.id.category_admin);
            tou_admin = (TextView) itemView.findViewById(R.id.tou_admin);
            pgitlink_admin =(TextView) itemView.findViewById(R.id.pgitlink_admin);



        }

        @Override
        public void onClick(View view) {
            int pos =getAdapterPosition();
            adminItem adminItem = ui.get(pos);



            Intent text = new Intent(context,admin_desc.class);


            text.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            text.putExtra("header",adminItem.getHead_admin());
                text.putExtra("id",adminItem.getId_admin());
                text.putExtra("description",adminItem.getDesc_admin());
                text.putExtra("email",adminItem.getEmail_admin());
                text.putExtra("domain",adminItem.getDomain_admin());
                text.putExtra("category",adminItem.getCategory_admin());
                text.putExtra("tou",adminItem.getTou_admin());
                text.putExtra("pgl",adminItem.getPro_git_link_admin());
                context.startActivity(text);




            // Log.d("pos ", String.valueOf(newestItem));
            //  head_newest = (TextView) itemView.findViewById(R.id.head_newest);

        }
    }
}
