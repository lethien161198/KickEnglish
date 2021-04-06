package com.example.myapplication.Models;
public class CustomEvent {
    private long a;
    private int background;

    public CustomEvent(long a, int background) {
        this.a = a;
        this.background = background;
    }

    public long getA() {
        return a;
    }

    public void setA(long a) {
        this.a = a;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}
