package com.example.rbt_hello;

import com.google.gson.annotations.SerializedName;

public class MenuItem {
    @SerializedName("value")
    private String value;
    @SerializedName("color")
    private String color;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
