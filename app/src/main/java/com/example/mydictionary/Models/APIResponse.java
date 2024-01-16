package com.example.mydictionary.Models;

import java.util.List;

public class APIResponse {
    String word = "";
    List<Phonetics> phonetics = null;
    List<Meanings> meanings = null;

    public String getWord() {
        return word;
    }

    public List<Phonetics> getPhonetics() {
        return phonetics;
    }

    public List<Meanings> getMeanings() {
        return meanings;
    }

}
