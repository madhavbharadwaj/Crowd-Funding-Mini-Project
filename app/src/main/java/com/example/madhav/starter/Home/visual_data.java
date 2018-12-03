package com.example.madhav.starter.Home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.EventLogTags;
import android.view.View;
import android.widget.Toast;

import com.example.madhav.starter.R;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class visual_data extends AppCompatActivity {
   // int exp_value;
    int app_value;
    int pen_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_data);


        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_vr);

        setSupportActionBar(toolbar);


        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_back));
        getSupportActionBar().setTitle("Visual Representation");

       /* Toast.makeText(getApplication(),getIntent().getStringExtra("explore"),
                Toast.LENGTH_SHORT).show();*/

        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);


        pieChart.setDrawHoleEnabled(false);
        pieChart.setDescription("");
        ArrayList NoOfEmp = new ArrayList();

        //exp_value = Integer.parseInt(getIntent().getStringExtra("explore"));
        app_value   = Integer.parseInt(getIntent().getStringExtra("approved"));
        pen_value    =Integer.parseInt(getIntent().getStringExtra("upcoming"));
        //NoOfEmp.add(new Entry(exp_value, 0));
        NoOfEmp.add(new Entry(app_value, 1));
        NoOfEmp.add(new Entry(pen_value, 2));

        PieDataSet dataSet = new PieDataSet(NoOfEmp, "");

        ArrayList year = new ArrayList();


        year.add("Approved");
        year.add("Upcoming");


        PieData data = new PieData(year, dataSet);
        data.setValueTextSize(15f);


        pieChart.setData(data);
       pieChart.setTouchEnabled(false);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        pieChart.animateXY(3000, 3000);

        //back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

    }
}
