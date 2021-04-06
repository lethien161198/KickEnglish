package com.example.myapplication.Models;

import android.widget.TextView;

public class TextModel {
    private TextView textView;
    private int color;

    public TextModel(TextView textView, int color) {
        this.textView = textView;
        this.color = color;
        this.textView.setBackgroundColor(this.color);
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
