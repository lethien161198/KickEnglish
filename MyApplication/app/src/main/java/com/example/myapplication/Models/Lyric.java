package com.example.myapplication.Models;

public class Lyric {
    private long time;
    private String content;
    public Lyric(long time, String content) {
        this.time = time;
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
