package com.example.mydictionary.Models;

public class Phonetics {
    String text = "";
    String audio = "";

    public String getText() {
        return text;
    }

    public String getAudio() {
        return audio;
    }


    @Override
    public String toString() {
        return "Phonetics{" +
                "text='" + text + '\'' +
                ", audio='" + audio + '\'' +
                '}';
    }
}
