package com.example.madhav.starter.login_signup.project_status;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
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
import com.example.madhav.starter.model.adapter_approved;
import com.example.madhav.starter.model.adapter_newest;
import com.example.madhav.starter.model.approvedItem;
import com.example.madhav.starter.model.newestItem;
import com.example.madhav.starter.network.mAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class approved extends Fragment {


    public approved() {
        // Required empty public constructor
    }
    private RecyclerView recyclerView_approved;
    private RecyclerView.Adapter adapter_approved;
    private List<approvedItem> approvedItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_approved, container, false);

        recyclerView_approved = (RecyclerView) v.findViewById(R.id.recyclerView_approved);
        recyclerView_approved.setHasFixedSize(true);
        recyclerView_approved.setLayoutManager(new LinearLayoutManager(getActivity()));
        //app_count = v.findViewById(R.id.app_count);

        approvedItems = new ArrayList<>();
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


        loadRecyclerViewData_approved();

        return v;

    }

    private void loadRecyclerViewData_approved()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        SharedPreferences prefs = getActivity().getSharedPreferences("email_pref",MODE_PRIVATE);
        String restoredText = prefs.getString("email", null);
        Log.d("email",restoredText);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                mAPI.APP_COUNT_USER + restoredText,

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

                                approvedItem ui = new approvedItem(
                                        //array1.getString("email"),
                                        student.getString("title"),
                                        student.getString("description"),
                                        student.getString("email"),
                                        student.getString("domain"),
                                        student.getString("category"),
                                        student.getString("upload_time"),
                                        student.getString("git_proj_link")
                                );
                                approvedItems.add(ui);
                            }
                            adapter_approved = new adapter_approved(approvedItems,getActivity());
                            //app_count.setText("Approved Projects : "+array.length());
                            recyclerView_approved.setAdapter(adapter_approved);



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
