package com.example.project2;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project2.R;
import com.example.project2.Superhero;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Superhero> list;
    CustomAdapter ca;
    TextView descriptiontext;
    TextView stats;
    final static String KEY = "86124378961743986345631442349876512765127";
    final static String KEY2 = "3214235675798707990890879877865787678576";

    final static String KEYintel = "1flight1";
    final static String KEYstrength = "2strength2";
    final static String KEYflight = "3flight3";
    final static String KEYname = "4name4";
    final static String KEYclicked = "55555";

    String desc;
    boolean flight;
    int intel;
    int str;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descriptiontext = findViewById(R.id.descview);

        stats = findViewById(R.id.STATS);

        lv = findViewById(R.id.listid);
        list = new ArrayList();

        list.add(new Superhero("Iron Man", R.drawable.ironman, "Iron man has a robotic suit that is equipped with repulsors, flight boosters, and more weapons to help him in combat. In Avengers Endgame, he sacrificed his life to wipe out Thanos and his army with the 6 infinity stones.", true, 60, 100));
        list.add(new Superhero("Captain America", R.drawable.captain, "Captain America has a vibranium shield that can take down enemies. He also has the power to wield Mjolnir, Thor's original hammer. After Endgame, Captain America returned the Infinity Stones to their original timeline, however, in the process, he stayed back in a different timeline to live out a full life that he never got the chance to live.", false, 80, 80));
        list.add(new Superhero("Thor", R.drawable.thor, "Thor has the power of thunder/lightning, and he uses the weapon Stormbreaker. It can summon lightning and the bifrost. After Endgame, Thor joined the Guardians of the Galaxy.", true, 100, 70));
        list.add(new Superhero("Hulk", R.drawable.hulk, "The Hulk is a normal scientist named Bruce Banner, and due to an experiment gone wrong, emitted radiation caused him to turn into the green giant. He is one of the strongest avengers, and he found a way to control the green giant he can turn into.", false, 100, 90));
        list.add(new Superhero("Black Widow", R.drawable.blackwidow, "Black widow is an agent who is very skilled in many types of combat. She was a Russian spy and she sacrificed her life so the Avengers could obtain the Soul Stone to bring back 50% of the universe that Thanos wiped out.", false, 50, 80));
        list.add(new Superhero("Hawkeye", R.drawable.hawkeye, "Hawkeye is another very skilled agent who mainly uses a bow and arrow. He uses a variety of arrows with different effects. I recommend watching the Hawkeye Disney+ series (final episode on Wednesday 12/22/2021!)", false, 40, 80));
        list.add(new Superhero("Spider Man", R.drawable.spiderman, "Spiderman is a superhero who was bitten by a radioactive spider. He has super strength, speed, and he's able to shoot webs from his web slingers. I would talk about Spider-Man No Way Home but I don't know if you (the viewer) watched it yet. No spoilers here!", false, 70, 90));
        list.add(new Superhero("Falcon", R.drawable.falcon, "Falcon is a superhero who is equipped with a falcon-styled suit that gives him the power of flight. He also has many weapons such as guns to help him in combat. He has become the new Captain America and is now in possession of the Vibranium shield.", true, 40, 70));
        list.add(new Superhero("Vision", R.drawable.vision, "Vision is an android who is made of vibranium, and he has the mind stone (one of the infinity sontes) embedded inside him. He was originally designed as a upgraded body for Ultron to use to destroy the world. The original Vision was killed in Avengers Infinity war by Thanos.", true, 90, 100));
        list.add(new Superhero("Ant Man", R.drawable.antman, "Ant man is a superhero who has a powerful suit that allows him to grow and shrink to almost any size. With the help of quantum technology, he is able to shrink to the quantum realm. In Ant-Man and the Wasp: Quantumania, (to be released in 2023), he will possibly meet Kang the Conqueror through the quantum realm.", false, 90, 70));

        //Log.d("TAG_LOG","oncreate"+list.toString());
        if (savedInstanceState==null) {
            ca = new CustomAdapter(MainActivity.this, R.layout.customadapter, list);
            lv.setAdapter(ca);
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                desc = ca.mylist.get(position).getDescription();

                flight = ca.mylist.get(position).getFlight();
                str = ca.mylist.get(position).getStrength();
                intel = ca.mylist.get(position).getInteligence();
                name = ca.mylist.get(position).getName();

                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    descriptiontext.setText(desc);
                }
                stats.setText(name + "\n" + "Flight: " + flight + "\n" +
                        "Strength: " + str + "\n" +
                        "Inteligence: " + intel);

            }
        });



    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY, (ArrayList<? extends Parcelable>) ca.mylist);
        outState.putString(KEY2, desc);

        outState.putBoolean(KEYflight, flight);
        outState.putInt(KEYstrength, str);
        outState.putInt(KEYintel, intel);
        outState.putString(KEYname, name);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        desc = savedInstanceState.getString(KEY2);

        list = savedInstanceState.getParcelableArrayList(KEY);
        ca = new CustomAdapter(this, R.layout.customadapter, list);
        lv.setAdapter(ca);

        ca.notifyDataSetChanged();

        if(desc != null && getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            descriptiontext.setText(desc);
        }
        else if (desc == null && getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            descriptiontext.setText("Description Of Selected Superhero");
        }

        flight = savedInstanceState.getBoolean(KEYflight);
        str = savedInstanceState.getInt(KEYstrength);
        intel = savedInstanceState.getInt(KEYintel);
        name = savedInstanceState.getString(KEYname);

        if(name != null) {
            stats.setText(name + "\n" + "Flight: " + flight + "\n" +
                    "Strength: " + str + "\n" +
                    "Inteligence: " + intel);
        }
        else{
            stats.setText("Hero Stats");
        }

    }
}

