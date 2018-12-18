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
import com.example.madhav.starter.model.adapter_newest_icl;
import com.example.madhav.starter.model.adapter_newest_mca;
import com.example.madhav.starter.model.newestItemIcl;
import com.example.madhav.starter.model.newestItemMca;
import com.example.madhav.starter.network.mAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class newestIcl extends Fragment {

    private RecyclerView recyclerView_new_icl;
    private RecyclerView.Adapter adapter_new_icl;
    private List<newestItemIcl> newestIclItems;
    public newestIcl() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_newest_icl, container, false);
        recyclerView_new_icl = (RecyclerView) v.findViewById(R.id.recyclerView_newest_icl);
        recyclerView_new_icl.setHasFixedSize(true);
        recyclerView_new_icl.setLayoutManager(new LinearLayoutManager(getActivity()));
        //app_count = v.findViewById(R.id.app_count);

        newestIclItems = new ArrayList<>();
        loadRecyclerViewData_newest_icl();
        return v;
    }

    private void loadRecyclerViewData_newest_icl()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading data ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                mAPI.NEWEST_ICL_URL,

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

                                newestItemIcl ui = new newestItemIcl(
                                        //array1.getString("email"),
                                        student.getString("title"),
                                        student.getString("description"),
                                        student.getString("email"),
                                        student.getString("domain"),
                                        student.getString("category"),
                                        student.getString("upload_time"),
                                        student.getString("git_proj_link")
                                );
                                newestIclItems.add(ui);
                            }
                            adapter_new_icl = new adapter_newest_icl(newestIclItems,getActivity());
                            //app_count.setText("Approved Projects : "+array.length());
                            recyclerView_new_icl.setAdapter(adapter_new_icl);



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
