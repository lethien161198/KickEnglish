package com.example.myapplication.Models;

public class Subtitles {
    private String content;
    private long start,end;

    public Subtitles(String content, long start, long end) {
        this.content = content;
        this.start = start;
        this.end = end;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
