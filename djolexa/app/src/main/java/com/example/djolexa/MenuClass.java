package com.example.djolexa;

import com.google.gson.annotations.SerializedName;

public class MenuClass  {
    @SerializedName("id")
    private String id;

    @SerializedName("value")
    private String value;

    @SerializedName("popup")
    private PopupClass popup;

    public String toString() {
        return popup.toString();
    }
}
