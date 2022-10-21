package com.example.final_project;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class pageWorkoutList extends Fragment {

    ListView listView;
    CustomAdapter c;
    ArrayList names;
    ArrayList times;
    ArrayList distances;

    ArrayList<Workout> workoutList;
    MainWorkoutActivity.DataAccessObject DAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        names = new ArrayList();
        times = new ArrayList();
        distances = new ArrayList();

        View v = inflater.inflate(R.layout.fragment_page_workout_list, container, false);

        DAO = new MainWorkoutActivity.DataAccessObject();
        workoutList = new ArrayList<>();
        listView = v.findViewById(R.id.id_workoutListView);

        names = (ArrayList) DAO.getNamesArray().clone();
        times = (ArrayList) DAO.getTimesArray().clone();
        distances = (ArrayList) DAO.getDistanceArray().clone();


        for (int i = 0; i < DAO.workoutcount; i++) {
            workoutList.add(new Workout(names.get(i).toString(),
                    (long) times.get(i),
                    (long) distances.get(i)));
        }

            c = new CustomAdapter(getActivity(), R.layout.customadapter, workoutList);
            listView.setAdapter(c);

        if(DAO.dataIsEmpty()){
            Toast("You do not have any logged workouts.");
        }

        return v;
    }


    public class CustomAdapter extends ArrayAdapter<Workout> {
        Context mainContext;
        List<Workout> List;
        Context context;

        public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Workout> objects) {
            super(context, resource, objects);
            mainContext = context;
            List = objects;
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)mainContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.customadapter, null);

            ImageView imageName = view.findViewById(R.id.id_imageName);
            ImageView imageTime = view.findViewById(R.id.id_imageTime);
            ImageView imageDistance = view.findViewById(R.id.id_imageDistance);

            imageName.setImageResource(R.drawable.name);
            imageTime.setImageResource(R.drawable.stopwatch);
            imageDistance.setImageResource(R.drawable.distance);

            TextView names = view.findViewById(R.id.id_dataTextName);
            TextView time = view.findViewById(R.id.id_dataTextSeconds);
            TextView distance = view.findViewById(R.id.id_dataTextDistance);

            names.setText(List.get(position).getName());
            time.setText(List.get(position).getSecondsSpent() + " seconds");
            distance.setText(""+List.get(position).getDistance() + " meters");

            return view;
        }
    }

    void Toast(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}