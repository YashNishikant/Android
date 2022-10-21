package com.example.final_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class pageGraph extends Fragment {

    ArrayList distances;
    ArrayList names;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_page_graph, container, false);

        distances = new ArrayList();
        distances = (ArrayList) MainWorkoutActivity.DataAccess.getDistanceArray().clone();

        names = new ArrayList();
        names = (ArrayList) MainWorkoutActivity.DataAccess.getNamesArray().clone();


        GraphView graph = (GraphView) v.findViewById(R.id.id_graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

        for(int i = 0; i < MainWorkoutActivity.DataAccess.getWorkoutCount(); i++){

            if(!names.get(i).toString().equalsIgnoreCase(""))
                series.appendData(new DataPoint(i,((Long)distances.get(i)).intValue()), true, 100);
        }

        graph.addSeries(series);


        return v;
    }
}