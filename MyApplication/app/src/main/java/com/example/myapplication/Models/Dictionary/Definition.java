package com.example.myapplication.Models.Dictionary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Definition {
    @SerializedName("definition")
    @Expose
    private String definition;
    @SerializedName("example")
    @Expose
    private String example;
    @SerializedName("synonyms")
    @Expose
    private List<String> synonyms = null;

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }
}
