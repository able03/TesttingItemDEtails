package com.example.testtingitemdetails;

public class CardStaticModel
{
    private static String name;
    private static int age;

    public CardStaticModel(){}
    public CardStaticModel(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public static String getName()
    {
        return name;
    }

    public static int getAge()
    {
        return age;
    }
}
