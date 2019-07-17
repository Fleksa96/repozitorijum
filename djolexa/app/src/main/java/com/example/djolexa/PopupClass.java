package com.example.djolexa;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PopupClass {
    @SerializedName("menuitem")
    private ArrayList<MenuItemClass> arrayList = new ArrayList<MenuItemClass>();

    public String toString() {
        StringBuilder builder = new StringBuilder("");
        for(MenuItemClass m : arrayList) {
            builder.append(m.toString());
            builder.append("\n");
        }

        return builder.toString();
    }
}
