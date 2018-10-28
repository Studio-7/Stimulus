package com.example.stimulus.API.Model.Children;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Children {

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @SerializedName("data")
    @Expose
    private Data data;

}
