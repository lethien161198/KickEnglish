package com.example.myapplication.Models.Dictionary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phonetic {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("audio")
    @Expose
    private String audio;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

}
