package com.example.thispc.mobilequiz;

/**
 * Created by this pc on 06-06-2016.
 */
public class RandomQuestionsType {
    private int id1;
    private int id2;
    private String type;


    public RandomQuestionsType(int id1, int id2, String type) {
        this.id1 = id1;
        this.type = type;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId1() {

        return id1;
    }

    public int getId2() {
        return id2;
    }

    public String getType() {
        return type;
    }
}