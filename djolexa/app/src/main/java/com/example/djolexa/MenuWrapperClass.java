package com.example.djolexa;

import com.google.gson.annotations.SerializedName;

public class MenuWrapperClass {
    @SerializedName("menu")
    private MenuClass menuClass;

    public String toString() {
        return menuClass.toString();
    }
}
