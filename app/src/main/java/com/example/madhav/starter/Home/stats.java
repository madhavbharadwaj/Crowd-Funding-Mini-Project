package com.example.madhav.starter.Home;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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
   // TextView total;
    CardView visual_rep;
    Button gPie;

    float exp_float;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // set_data d = new set_data();
        View v =  inflater.inflate(R.layout.fragment_stats, container, false);
        //exp_count = v.findViewById(R.id.exp_count);

        app_count = v.findViewById(R.id.app_count);

        upc_count = v.findViewById(R.id.upc_count);

        //total = v.findViewById(R.id.tot_count);
        visual_rep = v.findViewById(R.id.visual_rep);
        //gPie = v.findViewById(R.id.gPie);




        explore_count();
        //newest_count();
        pending_count();

       // Log.d("123",d.getApp_d());

       // String x = dodata();

        //d.getApp_d();

        //        Log.d("123",d.getApp_d());



        //add();

        visual_rep.setOnClickListener(new View.OnClickListener() {
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


        /*
        PieChart pieChart = (PieChart) v.findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        ArrayList NoOfEmp = new ArrayList();

        NoOfEmp.add(new Entry(945f, 0));
        NoOfEmp.add(new Entry(1040f, 1));
        NoOfEmp.add(new Entry(1133f, 2));
        NoOfEmp.add(new Entry(1240f, 3));
        NoOfEmp.add(new Entry(1369f, 4));
        NoOfEmp.add(new Entry(1487f, 5));
        NoOfEmp.add(new Entry(1501f, 6));
        NoOfEmp.add(new Entry(1645f, 7));
        NoOfEmp.add(new Entry(1578f, 8));
        NoOfEmp.add(new Entry(1695f, 9));
        PieDataSet dataSet = new PieDataSet(NoOfEmp, "Number Of Employees");

        ArrayList year = new ArrayList();

        year.add("2008");
        year.add("2009");
        year.add("2010");
        year.add("2011");
        year.add("2012");
        year.add("2013");
        year.add("2014");
        year.add("2015");
        year.add("2016");
        year.add("2017");
        PieData data = new PieData(year, dataSet);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(5000, 5000);*/

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
                            //Log.d("explore count", String.valueOf(array.length()));

                            String ec = String.valueOf(array.length());

                           // d.setApp_d(ec);
                            //Log.d("123",d.getApp_d());

                            //send(array.length());
                            app_count.setText(ec);
                            //add();
                            // yvalues.add(new Entry(new_float, 1));
                            // yvalues.add(new Entry(pend_float, 2));

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

   /* private Float send(int a) {
        return Float.valueOf(a);


    }*/


   /* private void newest_count()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading data ...");
        progressDialog.setCancelable(false);

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                mAPI.NEWEST_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("COMPLETE_DETAILS");
                           // Log.d("newest count", String.valueOf(array.length()));
                            String nc = String.valueOf(array.length());
                            new_count.setText(nc);

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
    }*/


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

}

