package com.example.mydictionary.Models;

import java.util.List;

public class Meanings {
    String partOfSpeech = "";
    List<Definitions> definitions = null;

    public String getPartOfSpeech() {
        return partOfSpeech;
    }


    public List<Definitions> getDefinitions() {
        return definitions;
    }

}
