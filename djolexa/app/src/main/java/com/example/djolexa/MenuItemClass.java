package com.example.djolexa;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MenuItemClass {
    @SerializedName("value")
    private String value;
    @SerializedName("color")
    private String color;

    public String toString() {
        return value + " : " + color;
    }
}
