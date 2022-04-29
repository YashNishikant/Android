package com.example.projectforvideo;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class Superhero implements  Parcelable{
    private String name;
    private String desc;
    private int id;
    private boolean flight;
    private int strength;
    private int inteligence;

    public Superhero(String name, int drawableid, String Description, boolean f, int strength, int intel){
        this.name = name;
        desc = Description;
        id = drawableid;
        flight = f;
        this.strength = strength;
        inteligence = intel;
    }

    protected Superhero(Parcel in) {
        name = in.readString();
        desc = in.readString();
        id = in.readInt();
    }

    public static final Creator<Superhero> CREATOR = new Parcelable.Creator<Superhero>() {
        @Override
        public Superhero createFromParcel(Parcel in) {
            return new Superhero(in);
        }

        @Override
        public Superhero[] newArray(int size) {
            return new Superhero[size];
        }
    };

    public String getName(){
        return name;
    }

    public String getDescription(){
        return desc;
    }

    public int getDrawId(){
        return id;
    }

    public int describeContents() {
        return 0;
    }

    public boolean getFlight(){
        return flight;
    }

    public int getInteligence(){
        return inteligence;
    }

    public int getStrength(){
        return strength;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(desc);
        parcel.writeInt(id);
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
