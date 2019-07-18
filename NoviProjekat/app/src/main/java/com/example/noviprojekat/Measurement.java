package com.example.noviprojekat;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Measurement implements Parcelable{
    @SerializedName("id")
    private int id;
    @SerializedName("temperature")
    private double temperature;
    @SerializedName("pollution")
    private double pollution;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("humidity")
    private double humidity;

    public Measurement(int id, double temperature, double pollution, String timestamp, double humidity) {
        this.id = id;
        this.temperature = temperature;
        this.pollution = pollution;
        this.timestamp = timestamp;
        this.humidity = humidity;
    }

    protected Measurement(Parcel in) {
        humidity = in.readDouble();
        id = in.readInt();
        pollution = in.readDouble();
        temperature = in.readDouble();
        timestamp = in.readString();

    }

    public static final Creator<Measurement> CREATOR = new Creator<Measurement>() {
        @Override
        public Measurement createFromParcel(Parcel in) {
            return new Measurement(in);
        }

        @Override
        public Measurement[] newArray(int size) {
            return new Measurement[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemperature() {
        return temperature+"";
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getPollution() {
        return pollution+"";
    }

    public void setPollution(double pollution) {
        this.pollution = pollution;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getHumidity() {
        return humidity+"";
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(humidity);
        parcel.writeInt(id);
        parcel.writeDouble(pollution);
        parcel.writeDouble(temperature);
        String t1 = timestamp.substring(0, 9);
        String t2 = timestamp.substring(11, 19);
        t1 = t2+ "  " + t1;
        parcel.writeString(t1);

    }
}
