package com.example.madhav.starter.Home.categories_icl;


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
import com.example.madhav.starter.model.adapter_upcoming_icl;
import com.example.madhav.starter.model.adapter_upcoming_mca;
import com.example.madhav.starter.model.upcomingItemIcl;
import com.example.madhav.starter.model.upcomingItemMca;
import com.example.madhav.starter.network.mAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class upcomingIcl extends Fragment {


    public upcomingIcl() {
        // Required empty public constructor
    }
    private RecyclerView recyclerView_upc_icl;
    private RecyclerView.Adapter adapter_upc_icl;
    private List<upcomingItemIcl> upcomingIclItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_upcoming_icl, container, false);
        recyclerView_upc_icl = (RecyclerView) v.findViewById(R.id.recyclerView_upc_icl);
        recyclerView_upc_icl.setHasFixedSize(true);
        recyclerView_upc_icl.setLayoutManager(new LinearLayoutManager(getActivity()));
        upcomingIclItems = new ArrayList<>();
        loadRecyclerViewData_upc_icl();


        return v;
    }
    private void loadRecyclerViewData_upc_icl()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading data ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                mAPI.UPCOMING_ICL_URL,

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

                                upcomingItemIcl ui = new upcomingItemIcl(
                                        //array1.getString("email"),
                                        student.getString("title"),
                                        student.getString("description"),
                                        student.getString("email"),
                                        student.getString("domain"),
                                        student.getString("category"),
                                        student.getString("upload_time"),
                                        student.getString("git_proj_link")

                                );
                                upcomingIclItems.add(ui);
                            }
                            adapter_upc_icl = new adapter_upcoming_icl(upcomingIclItems,getActivity());
                            //  pen_count.setText("Pending Projects : "+array.length());
                            recyclerView_upc_icl.setAdapter(adapter_upc_icl);



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
