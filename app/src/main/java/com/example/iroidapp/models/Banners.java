package com.example.iroidapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Banners implements Parcelable
{
    private String image;

    private String name;

    private String title;

    protected Banners(Parcel in) {
        image = in.readString();
        name = in.readString();
        title = in.readString();
    }

    public static final Creator<Banners> CREATOR = new Creator<Banners>() {
        @Override
        public Banners createFromParcel(Parcel in) {
            return new Banners(in);
        }

        @Override
        public Banners[] newArray(int size) {
            return new Banners[size];
        }
    };

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(title);
    }
}