package com.example.thispc.mobilequiz;

/**
 * Created by this pc on 06-06-2016.
 */
public class RandomQuestionsType
{
    private int id;
    private String type;


    public RandomQuestionsType(int id,String type)
    {
        this.id=id;
        this.type=type;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {

        return id;
    }

    public String getType() {
        return type;
    }
}
