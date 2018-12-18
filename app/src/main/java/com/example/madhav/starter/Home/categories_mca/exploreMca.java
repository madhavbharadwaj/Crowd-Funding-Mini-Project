package com.example.madhav.starter.Home.categories_mca;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.madhav.starter.R;
import com.example.madhav.starter.controller.VolleySingleton;
import com.example.madhav.starter.model.adapter_explore;
import com.example.madhav.starter.model.adapter_explore_mca;
import com.example.madhav.starter.model.exploreItem;
import com.example.madhav.starter.model.exploreItemMca;
import com.example.madhav.starter.network.mAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class exploreMca extends Fragment {


    private RecyclerView recyclerView_exp_mca;
    private RecyclerView.Adapter adapter_exp_mca;
    private List<exploreItemMca> exploreMcaItems;
    public exploreMca() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v =  inflater.inflate(R.layout.fragment_explore_mca, container, false);
        recyclerView_exp_mca = (RecyclerView) v.findViewById(R.id.recyclerView_explore_mca);
        recyclerView_exp_mca.setHasFixedSize(true);
        recyclerView_exp_mca.setLayoutManager(new LinearLayoutManager(getActivity()));
        exploreMcaItems = new ArrayList<>();
        loadRecyclerViewData_exploreMCA();
        return v;
    }

    private void loadRecyclerViewData_exploreMCA()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading data ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                mAPI.EXPLORE_MCA_URL,

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

                                exploreItemMca ui = new exploreItemMca(
                                        //array1.getString("email"),newest
                                        student.getString("title"),
                                        student.getString("description"),
                                        student.getString("email"),
                                        student.getString("domain"),
                                        student.getString("category"),
                                        student.getString("upload_time"),
                                        student.getString("git_proj_link")
                                );
                                exploreMcaItems.add(ui);
                            }
                            adapter_exp_mca = new adapter_explore_mca(exploreMcaItems,getActivity());

                            recyclerView_exp_mca.setAdapter(adapter_exp_mca);



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
