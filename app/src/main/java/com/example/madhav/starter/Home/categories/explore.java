package com.example.madhav.starter.Home.categories;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.madhav.starter.Home.add_project;
import com.example.madhav.starter.R;
import com.example.madhav.starter.controller.VolleySingleton;
import com.example.madhav.starter.login_signup.RegLogActivity;
import com.example.madhav.starter.login_signup.SaveSharedPreference;
import com.example.madhav.starter.model.adapter_explore;
import com.example.madhav.starter.model.adapter_newest;
import com.example.madhav.starter.model.exploreItem;
import com.example.madhav.starter.model.newestItem;
import com.example.madhav.starter.network.mAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class explore extends Fragment {

    Button b1;
    private RecyclerView recyclerView_exp;
    private RecyclerView.Adapter adapter_exp;
    private List<exploreItem> exploreItems;
    public explore() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v =  inflater.inflate(R.layout.fragment_explore, container, false);
        b1 = v.findViewById(R.id.btn_add);

        recyclerView_exp = (RecyclerView) v.findViewById(R.id.recyclerView_explore);
        recyclerView_exp.setHasFixedSize(true);
        recyclerView_exp.setLayoutManager(new LinearLayoutManager(getActivity()));


        exploreItems = new ArrayList<>();


       /* for(int i =0;i<=10;i++)
        {
            exploreItem ui = new exploreItem(
                    "heading" + (i+1),
                    "dummy text",
                    "2","4","5","5","5"


            );
            exploreItems.add(ui);
        }

        adapter_exp = new adapter_explore(exploreItems,getActivity());

        recyclerView_exp.setAdapter(adapter_exp);*/
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent intent = new Intent(getActivity(), add_project.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity(). finish();*/
                if (SaveSharedPreference.getLoggedStatus(getActivity().getApplicationContext())) {


                    //navUsername.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(getActivity(), add_project.class);


                    startActivity(intent);
                    getActivity().finish();

                } else {
                    // GetUser();

                    Toast.makeText(getActivity(), "Please login to upload projects",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), RegLogActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }

            }
        });

        loadRecyclerViewData_explore();

        return v;
    }

    private void loadRecyclerViewData_explore()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading data ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                mAPI.APPROVED_EXP_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("COMPLETE_DETAILS");
                            for(int i=0;i<array.length();i++){

                                JSONObject student = array.getJSONObject(i);
                                // JSONObject jo = student.getJSONObject("Student_details");

                                exploreItem ui = new exploreItem(
                                        //array1.getString("email"),newest
                                        student.getString("title"),
                                        student.getString("description"),
                                        student.getString("email"),
                                        student.getString("domain"),
                                        student.getString("category"),
                                        student.getString("upload_time"),
                                        student.getString("git_proj_link")
                                );
                                exploreItems.add(ui);
                            }
                            adapter_exp = new adapter_explore(exploreItems,getActivity());

                            recyclerView_exp.setAdapter(adapter_exp);



                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        );
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

}
