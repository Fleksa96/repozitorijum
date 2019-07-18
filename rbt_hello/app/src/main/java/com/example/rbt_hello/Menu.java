package com.example.rbt_hello;

import com.google.gson.annotations.SerializedName;

public class Menu {

    @SerializedName("id")
    private String id;
    @SerializedName("value")
    private String value;
    @SerializedName("popup")
    private MenuItems popup;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
