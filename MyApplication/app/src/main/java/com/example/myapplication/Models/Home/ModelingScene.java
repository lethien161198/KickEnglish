package com.example.myapplication.Models.Home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelingScene implements Serializable {
    @SerializedName("end")
    @Expose
    private Integer end;
    @SerializedName("start")
    @Expose
    private Integer start;

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}
