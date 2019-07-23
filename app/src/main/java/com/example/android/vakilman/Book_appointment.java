package com.example.android.vakilman;

/**
 * Created by user1 on 10/7/2018.
 */

public class Book_appointment {
    String userid;
    int day_of_month ;
    int month;
    int year;
    String username;
    public Book_appointment()
    {

    }
    public Book_appointment(String userid,int day_of_month,int month,int year,String username)
    {
        this.userid=userid;
        this.day_of_month=day_of_month;
        this.year=year;
        this.username=username;
    }
    public String getid()
    {
        return userid;
    }
    public int getDay_of_month()
    {
        return day_of_month ;
    }
    public int getmonth()
    {
        return month;
    }
    public int year()
    {
        return year;
    }
    public String getusername()
    {
        return username;
    }

}
