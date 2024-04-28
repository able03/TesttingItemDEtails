package com.example.testtingitemdetails;

public class CardModel
{
    private int id;
    private int imgRID;
    private String name;
    private String category;

    public CardModel(int id, int imgRID, String name, String category)
    {
        this.id = id;
        this.imgRID = imgRID;
        this.name = name;
        this.category = category;
    }

    public int getId()
    {
        return id;
    }

    public int getImgRID()
    {
        return imgRID;
    }

    public String getName()
    {
        return name;
    }

    public String getCategory()
    {
        return category;
    }
}
