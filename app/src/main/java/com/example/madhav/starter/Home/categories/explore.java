package com.example.madhav.starter.Home.categories;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.madhav.starter.Home.add_project;
import com.example.madhav.starter.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class explore extends Fragment {


    public explore() {
        // Required empty public constructor
    }
    Button b1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_explore, container, false);
        b1 = v.findViewById(R.id.btn_add);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), add_project.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity(). finish();
            }
        });

        return v;
    }

}
