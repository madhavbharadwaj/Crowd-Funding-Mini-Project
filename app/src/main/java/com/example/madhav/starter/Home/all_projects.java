package com.example.madhav.starter.Home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.madhav.starter.R;
import com.example.madhav.starter.login_signup.RegLogActivity;
import com.example.madhav.starter.login_signup.SaveSharedPreference;
import com.example.madhav.starter.login_signup.profile;

/**
 * A simple {@link Fragment} subclass.
 */
public class all_projects extends Fragment {


    public all_projects() {
        // Required empty public constructor
    }



    Button b1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_all_projects, container, false);





        b1 = v.findViewById(R.id.btn_add);
/*        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SaveSharedPreference.getLoggedStatus(getActivity().getApplicationContext())) {


                    //navUsername.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(getActivity(), add_project.class);


                    startActivity(intent);
                    getActivity().finish();

                } else {
                    // GetUser();


                    Intent intent = new Intent(getActivity(), RegLogActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });*/

/*
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), add_project.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
               getActivity(). finish();
            }
        });*/
        return v;



    }


}
