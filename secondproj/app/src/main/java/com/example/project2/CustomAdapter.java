package com.example.project2;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project2.R;
import com.example.project2.Superhero;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Superhero> {
    Context mainContext;
    List<Superhero> mylist;
    String selected;

    Context context;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Superhero> objects) {
        super(context, resource, objects);
        mainContext = context;
        mylist = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)mainContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.customadapter, null);
        ImageView imageview = view.findViewById(R.id.adapterImage);
        TextView names = view.findViewById(R.id.NAME);
        final Button remove = view.findViewById(R.id.removebutton);
        imageview.setImageResource(mylist.get(position).getDrawId());

        names.setText(mylist.get(position).getName());

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mylist.remove(position);
                notifyDataSetChanged();
            }
        });
        return view;
    }

}
