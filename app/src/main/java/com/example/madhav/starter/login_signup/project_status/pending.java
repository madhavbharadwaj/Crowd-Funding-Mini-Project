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
import com.example.madhav.starter.model.adapter_pending;
import com.example.madhav.starter.model.approvedItem;
import com.example.madhav.starter.model.pendingItem;
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
public class pending extends Fragment {


    public pending() {
        // Required empty public constructor
    }
    private RecyclerView recyclerView_pending;
    private RecyclerView.Adapter adapter_pending;
    private List<pendingItem> pendingItems;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pending, container, false);

        recyclerView_pending = (RecyclerView) v.findViewById(R.id.recyclerView_pending);
        recyclerView_pending.setHasFixedSize(true);
        recyclerView_pending.setLayoutManager(new LinearLayoutManager(getActivity()));
        //app_count = v.findViewById(R.id.app_count);

        pendingItems = new ArrayList<>();

        loadRecyclerViewData_approved();

        return  v;

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
                mAPI.PEND_COUNT_USER + restoredText,

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

                                pendingItem ui = new pendingItem(
                                        //array1.getString("email"),
                                        student.getString("title"),
                                        student.getString("description"),
                                        student.getString("email"),
                                        student.getString("domain"),
                                        student.getString("category"),
                                        student.getString("upload_time"),
                                        student.getString("git_proj_link")
                                );
                                pendingItems.add(ui);
                            }
                            adapter_pending = new adapter_pending(pendingItems,getActivity());
                            //app_count.setText("Approved Projects : "+array.length());
                            recyclerView_pending.setAdapter(adapter_pending);



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
