package com.deckofcardsapi.pojo;

public class Joker {

    private String jokers_enabled;

    public Joker(String jokers_enabled){
        this.jokers_enabled = jokers_enabled;
    }

    public void setJokers_enabled(String jokers_enabled){
        this.jokers_enabled = jokers_enabled;
    }

    public String getJokers_enabled(){
        return jokers_enabled;
    }
}
