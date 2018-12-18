package com.example.madhav.starter.Home;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.madhav.starter.R;
import com.example.madhav.starter.controller.VolleySingleton;
import com.example.madhav.starter.network.mAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class stats extends Fragment {


    public stats() {
        // Required empty public constructor
    }
    TextView app_count;
    TextView upc_count;

    TextView app_count_mca;
    TextView upc_count_mca;

    TextView app_count_icl;
    TextView upc_count_icl;

   // TextView total;
    CardView visual_rep;
    CardView visual_rep_mca;

    TextView t1;
    TextView t2;
    TextView t3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // set_data d = new set_data();
        View v =  inflater.inflate(R.layout.fragment_stats, container, false);
        //exp_count = v.findViewById(R.id.exp_count);
        t1 = (TextView) v.findViewById(R.id.show_stats_all);
        t2 = (TextView) v.findViewById(R.id.show_stats_mca);
        t3 = (TextView) v.findViewById(R.id.show_stats_icl);

        //for all projects
        app_count = v.findViewById(R.id.app_count);
        upc_count = v.findViewById(R.id.upc_count);
        visual_rep = v.findViewById(R.id.visual_rep_all);

        //for MCA projects
        app_count_mca = v.findViewById(R.id.app_count_mca);
        upc_count_mca = v.findViewById(R.id.upc_count_mca);
        visual_rep_mca = v.findViewById(R.id.visual_rep_mca);

        //for ICL projects
        app_count_icl = v.findViewById(R.id.app_count_icl);
        upc_count_icl = v.findViewById(R.id.upc_count_icl);





        //all projects
        explore_count();
        pending_count();


        //mca projects
        explore_count_mca();
        pending_count_mca();

        //icl projects
        explore_count_icl();
        pending_count_icl();


        SpannableString content = new SpannableString("Show Visual Data");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        t1.setText(content);

        SpannableString content1 = new SpannableString("Show Visual Data");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        t2.setText(content1);

        SpannableString content2 = new SpannableString("Show Visual Data");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        t3.setText(content2);



        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(getActivity(), exp_count.getText(),
                        Toast.LENGTH_SHORT).show();*/

                Intent it = new Intent(getActivity(), visual_data.class);
                //Log.d("123","test"+new_count.getText());

                //it.putExtra("explore", exp_count.getText());
                it.putExtra("approved", app_count.getText());
                it.putExtra("upcoming", upc_count.getText());

                startActivity(it);

            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(getActivity(), exp_count.getText(),
                        Toast.LENGTH_SHORT).show();*/

                Intent it = new Intent(getActivity(), visual_data.class);
                //Log.d("123","test"+new_count.getText());

                //it.putExtra("explore", exp_count.getText());
                it.putExtra("approved", app_count_mca.getText());
                it.putExtra("upcoming", upc_count_mca.getText());

                startActivity(it);

            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(getActivity(), exp_count.getText(),
                        Toast.LENGTH_SHORT).show();*/

                Intent it = new Intent(getActivity(), visual_data.class);
                //Log.d("123","test"+new_count.getText());

                //it.putExtra("explore", exp_count.getText());
                it.putExtra("approved", app_count_icl.getText());
                it.putExtra("upcoming", upc_count_icl.getText());

                startActivity(it);

            }
        });

        return v;
    }


    private void explore_count()
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

                            String ec = String.valueOf(array.length());

                            app_count.setText(ec);


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


    private void pending_count()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading data ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                mAPI.PENDING_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("COMPLETE_DETAILS");
                           // Log.d("pending count", String.valueOf(array.length()));

                            String pc = String.valueOf(array.length());

                            upc_count.setText(pc);


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


    private void explore_count_mca()
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

                            String ec = String.valueOf(array.length());

                            app_count_mca.setText(ec);


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


    private void pending_count_mca()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading data ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                mAPI.UPCOMING_MCA_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("COMPLETE_DETAILS");
                            // Log.d("pending count", String.valueOf(array.length()));

                            String pc = String.valueOf(array.length());

                            upc_count_mca.setText(pc);


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

    private void explore_count_icl()
    {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading data ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                mAPI.EXPLORE_ICL_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("COMPLETE_DETAILS");

                            String ec = String.valueOf(array.length());

                            app_count_icl.setText(ec);


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


    private void pending_count_icl()
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
                            // Log.d("pending count", String.valueOf(array.length()));

                            String pc = String.valueOf(array.length());

                            upc_count_icl.setText(pc);


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

