package com.example.madhav.starter.Home.categories;


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
import com.example.madhav.starter.model.adapter_newest;
import com.example.madhav.starter.model.adapter_upcoming;
import com.example.madhav.starter.model.newestItem;
import com.example.madhav.starter.model.upcomingItem;
import com.example.madhav.starter.network.mAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class newest extends Fragment {


    public newest() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<newestItem> newestItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_newest, container, false);


        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_newest);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        newestItems = new ArrayList<>();
        /*for(int i =0;i<=10;i++)
        {
            upcomingItem ui = new upcomingItem(
                    "heading" + (i+1),
                    "dummy text"

            );
            upcomingItems.add(ui);
        }

        adapter = new adapter_upcoming(upcomingItems,getActivity());

        recyclerView.setAdapter(adapter);*/


        loadRecyclerViewData_newest();
        return v;

    }

    private void loadRecyclerViewData_newest()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                mAPI.APPROVED_URL,

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

                                newestItem ui = new newestItem(
                                        //array1.getString("email"),
                                        student.getString("title"),
                                        student.getString("description")
                                );
                                newestItems.add(ui);
                            }
                            adapter = new adapter_newest(newestItems,getActivity());

                            recyclerView.setAdapter(adapter);



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
