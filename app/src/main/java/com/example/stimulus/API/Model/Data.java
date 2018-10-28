package com.example.stimulus.API.Model;

import com.example.stimulus.API.Model.Children.Children;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {

    public ArrayList<Children> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Children> children) {
        this.children = children;
    }

    @SerializedName("children")
    @Expose
    private ArrayList<Children> children;

}
