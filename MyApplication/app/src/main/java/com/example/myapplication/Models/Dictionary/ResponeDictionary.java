package com.example.myapplication.Models.Dictionary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponeDictionary {
    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("phonetics")
    @Expose
    private List<Phonetic> phonetics = null;
    @SerializedName("meanings")
    @Expose
    private List<Meaning> meanings = null;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Phonetic> getPhonetics() {
        return phonetics;
    }

    public void setPhonetics(List<Phonetic> phonetics) {
        this.phonetics = phonetics;
    }

    public List<Meaning> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<Meaning> meanings) {
        this.meanings = meanings;
    }
}
